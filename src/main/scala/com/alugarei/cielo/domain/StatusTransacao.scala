package com.alugarei.cielo.domain

/**
 * Created by Gustavo on 07/02/2016.
 */
sealed trait StatusTransacao {
  def codigo: Int
  def desc: String
}

case object Criada extends StatusTransacao {val codigo = 0; val desc = "Transação Criada"}
case object EmAndamento extends StatusTransacao {val codigo = 1; val desc = "Transação em Andamento"}
case object Autenticada extends StatusTransacao {val codigo = 2; val desc = "Transação Autenticada"}
case object NaoAutenicada extends StatusTransacao {val codigo = 3; val desc = "Transação não Autenticada"}
case object Autorizada extends StatusTransacao {val codigo = 4; val desc = "Transação Autorizada"}
case object NaoAutorizada extends StatusTransacao {val codigo = 5; val desc = "Transação não Autorizada"}
case object Capturada extends StatusTransacao {val codigo = 6; val desc = "Transação Capturada"}
case object Cancelada extends StatusTransacao {val codigo = 9; val desc = "Transação Cancelada"}
case object EmAutenticacao extends StatusTransacao {val codigo = 10; val desc = "Transação em Autenticação"}
case object EmCancelamento extends StatusTransacao {val codigo = 12; val desc = "Transação em Cancelamento"}


object StatusTransacao {
  def from(codigo: Int) = codigo match {
    case Criada.codigo => Criada
    case EmAndamento.codigo => EmAndamento
    case Autenticada.codigo => Autenticada
    case NaoAutenicada.codigo => NaoAutenicada
    case Autorizada.codigo => Autorizada
    case Capturada.codigo => Capturada
    case Cancelada.codigo => Cancelada
    case EmAutenticacao.codigo => EmAutenticacao
    case EmCancelamento.codigo => EmCancelamento
  }
}
