package com.alugarei.cielo.domain

import scala.xml.Node

/**
 * Created by Gustavo on 31/01/2016.
 */
case class Erro(
               codigo: String,
               mensagem: String)

object Erro extends XmlDeserializable {

  type T = Erro

  override def fromXml(xml: Node): Erro = {
    Erro(
      codigo = (xml \ "codigo").text,
      mensagem = (xml \ "mensagem").text)
  }

}

