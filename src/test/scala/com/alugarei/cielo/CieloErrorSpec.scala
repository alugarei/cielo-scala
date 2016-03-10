package com.alugarei.cielo

import com.alugarei.cielo.domain._
import org.scalatest.MustMatchers._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

/**
 * Testes para retorno com erro ao enviar requisição inválida
 *
 * Created by Gustavo on 09/02/2016.
 */
class CieloErrorSpec extends FlatSpec with ScalaFutures {

  val cielo = new Cielo(Cielo.endPointTest)

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(30, Seconds), interval = Span(5, Seconds))

  val dadosPortador = DadosPortador(
    numero = "4012001038443335",
    validade = "201805",
    codigoSeguranca = Some("123")
  )

  val dadosEC = DadosEC("1006993069", "25fbb99741c739dd84d7b06ec78c9bac718838630f30b112d033ce2e621b34f3")

  val dadosPedido = DadosPedido(
    numero = "1",
    valor = 20d,
    moeda = Real,
    dataHora = "2011-12-07T11:43:37"
  )

  val formaPagamento = FormaPagamento(Bandeira.VISA, CreditoAVista, 1)

  "Cielo Erro Spec" should "requisitar pagamento" in {

    val dadosECNOK = DadosEC("1006993069", "25fbb99741c739dd84d7b06ec78c9bac718838630f30b112d033ce2e621b34f4")

    val requisicaoTransacao = RequisicaoTransacao(
      dadosEC = dadosECNOK,
      dadosPortador = dadosPortador,
      dadosPedido = dadosPedido,
      formaPagamento = formaPagamento,
      urlRetorno = "http://www.exemplo.com",
      autorizar = AutorizacaoDireta,
      capturar = true)

    val futureTransacao = cielo.enviarRequisicao(requisicaoTransacao)
    whenReady(futureTransacao) { result =>
      result.isLeft mustBe true
      result.left.get.codigo mustBe "002"
      println(s"mensagem ${result.left.get.mensagem}")
    }
  }
}
