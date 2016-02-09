package com.alugarei.cielo.domain

import scala.xml.Node

/**
 * Created by Gustavo on 09/02/2016.
 */

case class Cancelamento(
                         codigo: Int,
                         mensagem: String,
                         dataHora: String,
                         valor: Double
                         )

object Cancelamento extends XmlDeserializable {
  override type T = Cancelamento

  override def fromXml(xml: Node): Cancelamento = {
    Cancelamento(
      codigo = (xml \ "codigo").text.toInt,
      mensagem = (xml \ "mensagem").text,
      dataHora = (xml \ "data-hora").text,
      valor = Requisicao.valorFromCieloString((xml \ "valor").text))
  }
}
