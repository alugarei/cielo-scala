package com.alugarei.cielo.domain

import org.scalatest._
import org.scalatest.MustMatchers._
import org.scalatest.StreamlinedXmlEquality._
import scala.xml.XML

/**
  * Created by Gustavo on 03/02/2016.
  */
class DadosPortadorSpec extends FlatSpec {

   "DadosPortador" should "gerar XML com seus atributos" in {
     val dadosPortador = DadosPortador(
       numero = "4012001038443335",
       validade = "201508",
       indicadorCodSeguranca = 1,
       codigoSeguranca = Some("973"),
       nomePortador = None,
       token = None
     )
     val xml = XML.load(getClass.getResource("/xml/dadosPortador.xml"))

     dadosPortador.toXml mustEqual xml

   }

 }
