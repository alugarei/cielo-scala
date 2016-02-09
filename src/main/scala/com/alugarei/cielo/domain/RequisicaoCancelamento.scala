package com.alugarei.cielo.domain

import scala.xml.Elem

/**
 * Created by Gustavo on 09/02/2016.
 */
case class RequisicaoCancelamento(
                                   tid: String,
                                   dadosEC: DadosEC,
                                   valor: Double) extends Requisicao {

  override def toXml: Elem =
    <requisicao-cancelamento id={id} versao={versao}>
      <tid>{tid}</tid>
      {dadosEC.toXml}
      <valor>{Requisicao.valorToCieloString(valor)}</valor>
    </requisicao-cancelamento>

}
