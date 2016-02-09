package com.alugarei.cielo.domain

import scala.xml.Node

/**
 * Created by Gustavo on 09/02/2016.
 */
case class Autenticacao(
                         codigo: Int,
                         mensagem: String,
                         dataHora: String,
                         valor: Double,
                         eci: Int)

object Autenticacao extends XmlDeserializable {
  override type T = Autenticacao

  override def fromXml(xml: Node): Autenticacao = {
    Autenticacao(
      codigo = (xml \ "codigo").text.toInt,
      mensagem = (xml \ "mensagem").text,
      dataHora = (xml \ "data-hora").text,
      valor = (Requisicao.valorFromCieloString((xml \ "valor").text)),
      eci = (xml \ "eci").text.toInt)
  }
}