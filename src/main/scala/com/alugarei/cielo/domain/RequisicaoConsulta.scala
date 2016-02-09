package com.alugarei.cielo.domain

import scala.xml.Elem

/**
 * Created by Gustavo on 09/02/2016.
 */
case class RequisicaoConsulta(
                             tid: String,
                             dadosEC: DadosEC) extends Requisicao {
  override def toXml: Elem = {
    <requisicao-consulta id={id} versao={versao}>
      <tid>{tid}</tid>
      {dadosEC.toXml}
    </requisicao-consulta>
  }
}
