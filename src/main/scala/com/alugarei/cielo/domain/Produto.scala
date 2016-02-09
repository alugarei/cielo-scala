package com.alugarei.cielo.domain

/**
 * Created by Gustavo on 04/02/2016.
 */
sealed trait Produto {
  def codigo: String
  def desc: String
}

case object CreditoAVista extends Produto {val codigo = "1"; val desc = "Crédito à vista"}
case object ParceladoLoja extends Produto {val codigo = "2"; val desc = "Parcelado Loja"}
case object Debito extends Produto {val codigo = "A"; val desc = "Débito"}

object Produto {
  def from(codigo: String): Produto = codigo match {
    case CreditoAVista.codigo => CreditoAVista
    case ParceladoLoja.codigo => ParceladoLoja
    case Debito.codigo => Debito
  }
}