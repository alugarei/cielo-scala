package com.alugarei.cielo.domain

import com.alugarei.cielo.domain.StatusTransacao.StatusTransacao

import scala.xml.{NodeSeq, Node, Elem}

/**
 * Transação
 *
 * Created by Gustavo on 31/01/2016.
 *
 * @param versao
 * @param id
 * @param tid Identificador da transação
 * @param pan
 * @param dadosPedido
 * @param formaPagamento
 * @param status Código de status da transação
 * @param urlAutenticacao URL de redirecionamento à Cielo
 * @param captura Resultado da Captura
 *
 */
case class Transacao(
                      versao: String,
                      id: String,
                      tid: String,
                      pan: String,
                      dadosPedido: DadosPedido,
                      formaPagamento: FormaPagamento,
                      status: StatusTransacao,
                      autenticacao: Option[Autenticacao] = None,
                      autorizacao: Option[Autorizacao] = None,
                      urlAutenticacao: Option[String] = None,
                      captura: Option[Captura] = None,
                      cancelamentos: Seq[Cancelamento] = Seq.empty)

object Transacao extends XmlDeserializable {

  type T = Transacao

  def fromXml(xml: Node): Transacao = {
    val urlAutenticacao = (xml \ "url-autenticacao").text
    val maybeCapturaNode = (xml \ "captura").headOption
    val maybeAutenticacaoNode = (xml \ "autenticacao").headOption
    val autenticacao = if (maybeAutenticacaoNode.isDefined) Some(Autenticacao.fromXml(maybeAutenticacaoNode.get)) else None
    val maybeAutorizacaoNode = (xml \ "autorizacao").headOption
    val autorizacao = if (maybeAutorizacaoNode.isDefined) Some(Autorizacao.fromXml(maybeAutorizacaoNode.get)) else None
    Transacao(
      versao = xml \@ "versao",
      id = xml \@ "id",
      tid = (xml \ "tid").text,
      pan = (xml \ "pan").text,
      dadosPedido = DadosPedido.fromXml((xml \ "dados-pedido").head),
      formaPagamento = FormaPagamento.fromXml((xml \ "forma-pagamento").head),
      status = StatusTransacao((xml \ "status").text.toInt),
      autenticacao = autenticacao,
      autorizacao = autorizacao,
      urlAutenticacao = if (urlAutenticacao.isEmpty) None else Some(urlAutenticacao),
      captura = if (maybeCapturaNode.isDefined) Some(Captura.fromXml(maybeCapturaNode.get)) else None,
      cancelamentos = (xml \ "cancelamentos" \ "cancelamento") map {node => Cancelamento.fromXml(node)})
  }

}