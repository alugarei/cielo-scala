package com.alugarei.cielo.domain

/**
 * Enumeration para Status da Transação
 *
 * Created by Gustavo on 07/02/2016.
 */
object StatusTransacao extends Enumeration {
  type StatusTransacao = Value

  val Criada = Value(0, "Transação Criada")
  val EmAndamento = Value(1, "Transação em Andamento")
  val Autenticada = Value(2, "Transação Autenticada")
  val NaoAutenticada = Value(3, "Transação não Autenticada")
  val Autorizada = Value(4, "Transação Autorizada")
  val NaoAutorizada = Value(5, "Transação não Autorizada")
  val Capturada = Value(6, "Transação Capturada")
  val Cancelada = Value(9, "Transação Cancelada")
  val EmAutenticacao = Value(10, "Transação em Autenticação")
  val EmCancelamento = Value(12, "Transação em Cancelamento")

}
