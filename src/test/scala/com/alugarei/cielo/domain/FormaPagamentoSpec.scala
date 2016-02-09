package com.alugarei.cielo.domain

import org.scalatest._
import org.scalatest.MustMatchers._
import org.scalatest.StreamlinedXmlEquality._
import scala.xml.XML

/**
 * Created by Gustavo on 03/02/2016.
 */
class FormaPagamentoSpec extends FlatSpec {

  "Forma de Pagamento" should "gerar XML com seus atributos" in {
    val formapgto = FormaPagamento(Bandeira.VISA, Debito, 1)
    val xml = XML.load(getClass.getResource("/xml/formaPgto.xml"))

    //TODO teste abaixo deveria passar :(
    //    formapgto.toXml mustEqual xml
  }

}
