package com.alugarei.cielo.domain

/**
 * Created by Gustavo on 05/02/2016.
 */
sealed trait Autorizar {
  def codigo : String
}

case object NaoAutorizar extends Autorizar {val codigo = "0"} //Somente Autenticar
case object AutorizarSomenteSeAutenticada extends Autorizar  { val codigo = "1"}
case object AutorizarAutenticadaENaoAutenticada extends Autorizar { val codigo = "2"}
case object AutorizacaoDireta extends Autorizar { val codigo = "3"}
case object AutorizacaoRecorrente extends Autorizar { val codigo = "4"}

object Autorizar {

  def apply(codigo: String ): Autorizar = codigo match {
    case NaoAutorizar.codigo => NaoAutorizar
    case AutorizarSomenteSeAutenticada.codigo => AutorizarSomenteSeAutenticada
    case AutorizarAutenticadaENaoAutenticada.codigo => AutorizarAutenticadaENaoAutenticada
    case AutorizacaoDireta.codigo => AutorizacaoDireta
    case AutorizacaoRecorrente.codigo => AutorizacaoRecorrente
  }
}
