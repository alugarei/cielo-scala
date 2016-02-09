package com.alugarei.cielo.domain

/**
 * Dados do Portador
 *
 * @param numero Número do cartão
 * @param validade Validade do cartão no formato aaaamm
 * @param indicadorCodSeguranca Indicador sobre o envio do Código de segurança:
 *                  0 – não informado, 1 – informado, 2 – ilegível, 9 – inexistente
 * @param codigoSeguranca Obrigatório se o indicador for 1
 * @param nomePortador Nome como impresso no cartão
 * @param token Token que deve ser utilizado em substituição aos dados do cartão para uma
 *              autorização direta ou uma transação recorrente. Não é permitido o envio
 *              do token junto com os dados do cartão na mesma transação.
 *
 * Created by Gustavo on 03/02/2016.
 *
 */
case class DadosPortador(
                          numero: String,
                          validade: String,
                          indicadorCodSeguranca: Int = 1,
                          codigoSeguranca: Option[Int],
                          nomePortador: Option[String] = None,
                          token: Option[String] = None) extends XmlSerializable {

  def toXml = {
    <dados-portador>
      <numero>{numero}</numero>
      <validade>{validade}</validade>
      <indicador>{indicadorCodSeguranca.toString}</indicador>
      {if (codigoSeguranca.isDefined) <codigo-seguranca>{codigoSeguranca.get.toString}</codigo-seguranca>}
      {if (nomePortador.isDefined) <nome-portador>{nomePortador.get}</nome-portador>}
      {if (token.isDefined) <token>{token.get}</token>}
    </dados-portador>
  }
}
