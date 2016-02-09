package com.alugarei.cielo.domain

/**
 * Created by Gustavo on 04/02/2016.
 */
sealed trait Idioma {
  def codigo: String
}

case object PT extends Idioma {val codigo = "PT"}
case object ES extends Idioma {val codigo = "ES"}
case object EN extends Idioma {val codigo = "EN"}

object Idioma {
  def from(codigo: String) = codigo match {
    case PT.codigo => PT
    case ES.codigo => ES
    case EN.codigo => EN
  }
}
