package com.alugarei.cielo.domain

import org.scalatest._
import org.scalatest.MustMatchers._
import org.scalatest.StreamlinedXmlEquality._
import scala.xml.XML

/**
  * Created by Gustavo on 03/02/2016.
  */
class DadosPedidoSpec extends FlatSpec {

   "DadosPedido" should "gerar XML com seus atributos" in {
     val dadosPedido = DadosPedido(
       numero = "178148599",
       valor = 10,
       moeda = Real,
       dataHora = "2011-12-07T11:43:37",
       descricao = Some("[origem:10.50.54.156]"),
       idioma = Some(PT),
       taxaEmbarque = None,
       softDescriptor = None
     )
     val xml = XML.load(getClass.getResource("/xml/dadosPedido.xml"))

     dadosPedido.toXml mustEqual xml

   }

 }
