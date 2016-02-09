package com.alugarei.cielo.domain

import com.alugarei.cielo.domain.Bandeira.Bandeira

import scala.xml.Node

/**
 * Forma de Pagamento
 *
 * @param bandeira Nome da bandeira: “visa”, “mastercard”, “diners”, “discover”, “elo”, “amex”, “jcb”, “aura”
 * @param produto Código do produto
 * @param parcelas Número de parcelas. 1 para crédito às vista ou débito
 *
 *
 * Created by Gustavo on 03/02/2016.
 */
case class FormaPagamento(
                           bandeira: Bandeira,
                           produto: Produto,
                           parcelas: Int) extends XmlSerializable{
  def toXml =
    <forma-pagamento>
      <bandeira>{bandeira}</bandeira>
      <produto>{produto.codigo}</produto>
      <parcelas>{parcelas.toString}</parcelas>
    </forma-pagamento>

}

object FormaPagamento extends XmlDeserializable {

  type T = FormaPagamento

  def fromXml(xml: Node): FormaPagamento = {
    FormaPagamento(
      bandeira = Bandeira.withName((xml \ "bandeira").text),
      produto = Produto.from((xml \ "produto").text),
      parcelas = (xml \ "parcelas").text.toInt)
  }
}

object Bandeira extends Enumeration {

  type Bandeira = Value

  val VISA = Value("visa")
  val MASTERCARD = Value("mastercard")
  val DINNERS = Value("diners")
  val DISCOVER = Value("discover")
  val ELO = Value("elo")
  val AMEX = Value("amex")
  val JCB = Value("jcb")
  val AURA = Value("aura")


}