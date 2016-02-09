package com.alugarei.cielo.domain

import scala.xml.Node

/**
 * Created by Gustavo on 09/02/2016.
 */
case class Autorizacao(
                        codigo: Int,
                        mensagem: String,
                        dataHora: String,
                        valor: Double
                        )

object Autorizacao extends XmlDeserializable {
  override type T = Autorizacao

  override def fromXml(xml: Node): Autorizacao = {
    Autorizacao(
      codigo = (xml \ "codigo").text.toInt,
      mensagem = (xml \ "mensagem").text,
      dataHora = (xml \ "data-hora").text,
      valor = (Requisicao.valorFromCieloString((xml \ "valor").text)))
  }
}