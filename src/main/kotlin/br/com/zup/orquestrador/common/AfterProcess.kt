package br.com.zup.orquestrador.common

import br.com.zup.orquestrador.common.banco.Transacao

interface AfterProcess {

    fun processa(transacao: Transacao)
}