package com.alugarei.cielo.domain

/**
 * Created by Gustavo on 04/02/2016.
 */
sealed trait Moeda {
  def codigo: String
}

case object Real extends Moeda {val codigo = "986"}

object Moeda {
  def from(codigo: String): Moeda = codigo match {
    case Real.codigo => Real
  }
}