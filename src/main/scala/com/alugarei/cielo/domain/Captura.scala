package com.alugarei.cielo.domain

import scala.xml.Node

/**
 * Created by Gustavo on 09/02/2016.
 */
case class Captura(
                    codigo: Int,
                    mensagem: String,
                    dataHora: String,
                    valor: Double,
                    taxaEmbarque: Option[Double])

object Captura extends XmlDeserializable {

  override type T = Captura

  override def fromXml(xml: Node): Captura = {
    val taxaNode = (xml \ "taxa-embarque")
    val taxaEmbarque = if (taxaNode.nonEmpty) Some(Requisicao.valorFromCieloString(taxaNode.text)) else None
    Captura(
      codigo = (xml \ "codigo").text.toInt,
      mensagem = (xml \ "mensagem").text,
      dataHora = (xml \ "data-hora").text,
      valor = Requisicao.valorFromCieloString((xml \ "valor").text),
      taxaEmbarque = taxaEmbarque)
  }
}
