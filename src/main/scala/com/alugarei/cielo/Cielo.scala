package com.alugarei.cielo

import com.alugarei.cielo.domain._
import scalaj.http._

import scala.concurrent.Future
import scala.xml.{Elem, XML}

// provide an execution context

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by Gustavo on 04/02/2016.
 */
object Cielo {

  val endPoint = "https://qasecommerce.cielo.com.br/servicos/ecommwsec.do"

  private def getMensagemCielo(xml: Elem) = {
    val writer = new java.io.StringWriter
    XML.write(w = writer, node = xml, enc = "utf-8", xmlDecl = true, doctype = null)
    s"mensagem=${writer.toString}"
  }

  def enviarRequisicao(requisicaoTransacao: Requisicao): Future[Either[Erro, Transacao]] = Future {
    var mensagem: String = getMensagemCielo(requisicaoTransacao.toXml)
    val response = Http(endPoint).charset("ISO-8859-1").postData(mensagem).asString
    val responseXml = XML.loadString(response.body)

    if (responseXml.label == "erro") {
      val erro = Erro.fromXml(responseXml)
      println(s"Erro ao enviar requisição: Código ${erro.codigo} Mensagem: ${erro.mensagem}")
      Left(erro)
    }
    else {
      Right(Transacao.fromXml(responseXml))
    }
  }

}
