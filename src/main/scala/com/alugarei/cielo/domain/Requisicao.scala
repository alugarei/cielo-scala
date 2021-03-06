package com.alugarei.cielo.domain

import java.util.UUID

import scala.math.BigDecimal.RoundingMode
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

  val PRECISAO_VALOR = 2

  def valorToCieloString(valor: BigDecimal) = (valor.setScale(PRECISAO_VALOR, RoundingMode.HALF_EVEN) * 100).toInt.toString
  
  def valorFromCieloString(valor: String) = (BigDecimal(valor) / 100)

}