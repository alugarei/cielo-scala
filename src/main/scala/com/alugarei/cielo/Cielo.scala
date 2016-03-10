package com.alugarei.cielo

import com.alugarei.cielo.domain._
import scalaj.http._

import scala.concurrent.Future
import scala.xml.{Elem, XML}

// provide an execution context

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Classe responsável por enviar as requisições ao webservice Cielo
 *
 * @param endPoint Caminho da URL a ser utilizada como EndPoint. O objeto Cielo
 *                 possui o endereço dos endpoints de teste e produção.
 *
 * Created by Gustavo on 04/02/2016.
 */
class Cielo(endPoint: String) {
  private def getMensagemCielo(xml: Elem): String = {
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
      Left(erro)
    }
    else {
      Right(Transacao.fromXml(responseXml))
    }
  }
}

object Cielo {

  val endPointTest = "https://qasecommerce.cielo.com.br/servicos/ecommwsec.do"

  val endPointProd = "https://ecommerce.cielo.com.br/servicos/ecommwsec.do"

  val cieloDateTimeFmt: String = "aaaa-MM-ddTHH24:mm:ss"

}
