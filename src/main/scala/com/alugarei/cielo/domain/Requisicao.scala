package com.alugarei.cielo.domain

import java.util.UUID

import scala.xml.Elem

/**
 * Created by Gustavo on 08/02/2016.
 */
abstract class Requisicao extends XmlSerializable {

  val id = UUID.randomUUID().toString

  val versao = "1.4.0"

  def toXml: Elem

}

object Requisicao {

  def valorToCieloString(valor: Double) = (valor * 100).toInt.toString
  
  def valorFromCieloString(valor: String) = (valor.toDouble / 100)

}