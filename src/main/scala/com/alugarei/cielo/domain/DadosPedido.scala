package com.alugarei.cielo.domain

import scala.xml.Node

/**
 *
 * Dados do Pedido
 *
 * @param numero Número do pedido da loja.
 * @param valor valor a ser cobrado
 * @param moeda Código numérico da moeda na norma ISO 4217
 * @param dataHora Data-hora do pedido. Formato: aaaa-MM-ddTHH24:mm:ss
 * @param descricao Descrição do pedido
 * @param idioma PT (português), EN (inglês) ou ES (espanhol)
 * @param taxaEmbarque Valor destinado a taxa de embarque (voos)
 * @param softDescriptor Texto de até 13 caracteres que será exibido na fatura
 *                       do portador, após o nome do Estabelecimento Comercial.
 *
 * Created by Gustavo on 03/02/2016.
 */
case class DadosPedido(
                        numero: String,
                        valor: Double,
                        moeda: Moeda = Real,
                        dataHora: String,
                        descricao: Option[String] = None,
                        idioma: Option[Idioma] = None,
                        taxaEmbarque: Option[Int] = None,
                        softDescriptor: Option[String] = None) extends XmlSerializable {

  def toXml = {
    <dados-pedido>
      <numero>{numero}</numero>
      <valor>{Requisicao.valorToCieloString(valor)}</valor>
      <moeda>{moeda.codigo}</moeda>
      <data-hora>{dataHora}</data-hora>
      {if (descricao.isDefined) <descricao>{descricao.get}</descricao>}
      {if (idioma.isDefined) <idioma>{idioma.get.toString}</idioma>}
      {if (taxaEmbarque.isDefined) <taxa-embarque>{taxaEmbarque.get.toString}</taxa-embarque>}
      {if (softDescriptor.isDefined) <soft-descriptor>{softDescriptor.get}</soft-descriptor> }
    </dados-pedido>
  }
}

object DadosPedido extends XmlDeserializable {

  type T = DadosPedido

  def fromXml(xml: Node): DadosPedido = {
    val descricao = (xml \ "descricao").text
    val idioma = (xml \ "idioma").text
    val taxaEmbarque = (xml \ "taxa-embarque").text
    val softDescriptor = (xml \ "soft-descriptor").text

    DadosPedido(
      numero = (xml \ "numero").text,
      valor = Requisicao.valorFromCieloString((xml \ "valor").text),
      moeda = Moeda.from((xml \ "moeda").text),
      dataHora = (xml \ "data-hora").text,
      descricao = if (descricao.isEmpty) None else Some(descricao),
      idioma = if (idioma.isEmpty) None else Some(Idioma.from(idioma)),
      taxaEmbarque = if (taxaEmbarque.isEmpty) None else Some(taxaEmbarque.toInt),
      softDescriptor = if (softDescriptor.isEmpty) None else Some(softDescriptor))
  }
}
