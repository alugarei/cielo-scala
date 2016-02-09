package com.alugarei.cielo.domain

import java.util.UUID

import scala.xml.{Node, NodeSeq}

/**
 * Requisição da Transação
 *
 * @param dadosEC
 * @param dadosPortador
 * @param dadosPedido
 * @param formaPagamento
 * @param urlRetorno URL da página de retorno
 * @param capturar Define se a transação será automaticamente capturada caso seja autorizada.
 * @param campoLivre
 * @param bin Seis primeiros números do cartão.
 * @param gerarToken Define se a transação atual deve gerar um token associado ao cartão.
 * @param avs String contendo um bloco XML, encapsulado pelo CDATA,
 *            contendo as informações necessárias para realizar a consulta ao serviço.
 *
 * Created by Gustavo on 31/01/2016.
 */
case class RequisicaoTransacao(
                                dadosEC: DadosEC,
                                dadosPortador: DadosPortador,
                                dadosPedido: DadosPedido,
                                formaPagamento: FormaPagamento,
                                urlRetorno: String,
                                autorizar: Autorizar,
                                capturar: Boolean,
                                campoLivre: Option[String] = None,
                                bin: Option[Int] = None,
                                gerarToken: Option[Boolean] = None,
                                avs: Option[String] = None) extends Requisicao {
  def toXml =
    <requisicao-transacao id={id} versao={versao} xmlns="http://ecommerce.cbmp.com.br">
      {dadosEC.toXml}
      {dadosPortador.toXml}
      {dadosPedido.toXml}
      {formaPagamento.toXml}
      <url-retorno>{urlRetorno}</url-retorno>
      <autorizar>{autorizar.codigo}</autorizar>
      <capturar>{capturar}</capturar>
      {if (campoLivre.isDefined) <campo-livre>{campoLivre}</campo-livre>}
      {if (bin.isDefined) <bin>{bin}</bin>}
      {if (gerarToken.isDefined) <gerar-token>{gerarToken}</gerar-token>}
      {if (avs.isDefined) <avs>{avs}</avs>}
    </requisicao-transacao>

}
