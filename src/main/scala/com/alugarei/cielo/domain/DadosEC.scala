package com.alugarei.cielo.domain

/**
 * Dados do Estabelecimento Comercial
 *
 * @param numero Número de afiliação da loja junto a Cielo
 * @param chave Chave de acesso da loja atribuída pela Cielo.
 *
 * Created by Gustavo on 03/02/2016.
 */
case class DadosEC(numero: String, chave: String) extends XmlSerializable {

  def toXml =
    <dados-ec>
      <numero>{numero}</numero>
      <chave>{chave}</chave>
    </dados-ec>

}
