package br.com.zup.orquestrador.common.banco

import org.springframework.data.jpa.repository.JpaRepository

interface TransacaoRepository: JpaRepository<Transacao, Long>{}