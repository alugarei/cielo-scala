package com.alugarei.cielo.domain

import java.util.UUID

import scala.xml.Elem

/**
 * Requisição de Captura
 *
 * @param dadosEC
 * @param tid
 * @param valor
 *
 * Created by Gustavo on 08/02/2016.
 */
case class RequisicaoCaptura(
                              dadosEC: DadosEC,
                              tid: String,
                              valor: Double) extends Requisicao {

  def toXml: Elem =
    <requisicao-captura id={id} versao={versao} xmlns="http://ecommerce.cbmp.com.br">
      <tid>{tid}</tid>
      {dadosEC.toXml}
      <valor>{Requisicao.valorToCieloString(valor)}</valor>
    </requisicao-captura>
}
