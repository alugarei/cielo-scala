package com.alugarei.cielo

import com.alugarei.cielo.domain._
import org.scalatest._
import org.scalatest.MustMatchers._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

/**
 * Created by Gustavo on 04/02/2016.
 */
class CieloSpec extends FlatSpec with ScalaFutures {

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(30, Seconds), interval = Span(5, Seconds))

  val dadosPortador = DadosPortador(
    numero = "4012001038443335",
    validade = "201805",
    codigoSeguranca = Some(123)
  )

  val dadosEC = DadosEC("1006993069", "25fbb99741c739dd84d7b06ec78c9bac718838630f30b112d033ce2e621b34f3")

  val dadosPedido = DadosPedido(
    numero = "1",
    valor = 20d,
    moeda = Real,
    dataHora = "2011-12-07T11:43:37"
  )

  val formaPagamento = FormaPagamento(Bandeira.VISA, CreditoAVista, 1)

  val requisicaoTransacao = RequisicaoTransacao(
    dadosEC = dadosEC,
    dadosPortador = dadosPortador,
    dadosPedido = dadosPedido,
    formaPagamento = formaPagamento,
    urlRetorno = "http://www.exemplo.com",
    autorizar = AutorizacaoDireta,
    capturar = true)

  "Cielo" should "requisitar pagamento" in {
    val futureTransacao = Cielo.enviarRequisicao(requisicaoTransacao)
    whenReady(futureTransacao) { result =>
      result.isRight mustBe true
      result.right.get.status mustBe Capturada
      result.right.get.autorizacao mustBe defined
      result.right.get.autorizacao.get.valor mustBe 20d
      result.right.get.autenticacao mustBe defined
      result.right.get.autenticacao.get.valor mustBe 20d
      result.right.get.dadosPedido.numero mustBe dadosPedido.numero
      result.right.get.dadosPedido.valor mustBe dadosPedido.valor
      result.right.get.formaPagamento mustBe formaPagamento
      result.right.get.captura mustBe defined
      result.right.get.captura.get.valor mustBe 20d
    }
  }

  it should "capturar transação criada anteriormente" in {
    val requisicaoSemCaptura = RequisicaoTransacao(
      dadosEC = dadosEC,
      dadosPortador = dadosPortador,
      dadosPedido = dadosPedido,
      formaPagamento = formaPagamento,
      urlRetorno = "http://www.exemplo.com",
      autorizar = AutorizacaoDireta,
      capturar = false)

    val futureTransacao = Cielo.enviarRequisicao(requisicaoSemCaptura)
    whenReady(futureTransacao) { transacaoReq =>
      transacaoReq.isRight mustBe true
      transacaoReq.right.get.status mustBe Autorizada
      val requisicaoCaptura = RequisicaoCaptura(dadosEC, transacaoReq.right.get.tid, 10d)
      val futureTransacao = Cielo.enviarRequisicao(requisicaoCaptura)
      whenReady(futureTransacao) { transacaoCaptura =>
        transacaoCaptura.isRight mustBe true
        transacaoCaptura.right.get.status mustBe Capturada
      }
    }
  }

  it should "consultar transação" in {
    val futureTransacao = Cielo.enviarRequisicao(requisicaoTransacao)
    whenReady(futureTransacao) { transacaoReq =>
      transacaoReq.isRight mustBe true
      val requisicaoConsulta = RequisicaoConsulta(transacaoReq.right.get.tid, dadosEC)
      val futureTransacao = Cielo.enviarRequisicao(requisicaoConsulta)
      whenReady(futureTransacao) { transacao =>
        transacao.isRight mustBe true
        transacao.right.get.status mustBe Capturada
      }
    }
  }

  it should "cancelar uma transação parcialmente" in {
    val futureTransacao = Cielo.enviarRequisicao(requisicaoTransacao)
    whenReady(futureTransacao) { transacaoReq =>
      transacaoReq.isRight mustBe true
      val requisicaoCancelamento =  RequisicaoCancelamento(transacaoReq.right.get.tid, dadosEC, 10d)
      val futureTransacao = Cielo.enviarRequisicao(requisicaoCancelamento)
      whenReady(futureTransacao) { transacao =>
        transacao.isRight mustBe true
        transacao.right.get.status mustBe Capturada
        transacao.right.get.cancelamentos must not be empty
        transacao.right.get.cancelamentos.head.valor mustBe 10d
      }
    }
  }

  it should "cancelar uma trascação completamente" in {
    val futureTransacao = Cielo.enviarRequisicao(requisicaoTransacao)
    whenReady(futureTransacao) { transacaoReq =>
      transacaoReq.isRight mustBe true
      val requisicaoCancelamento =  RequisicaoCancelamento(transacaoReq.right.get.tid, dadosEC, 20d)
      val futureTransacao = Cielo.enviarRequisicao(requisicaoCancelamento)
      whenReady(futureTransacao) { transacao =>
        transacao.isRight mustBe true
        transacao.right.get.status mustBe Cancelada
        transacao.right.get.cancelamentos must not be empty
        transacao.right.get.cancelamentos.head.valor mustBe 20d
      }
    }
  }
}
