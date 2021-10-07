package br.com.zup.orquestrador.kafka

import br.com.zup.orquestrador.common.banco.Transacao
import br.com.zup.orquestrador.common.Operacao
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransacaoKafka(
    val operacao: Operacao,
    val valor: BigDecimal,
    val dataTransacao: LocalDateTime,
    val idCliente: Long,
    val idConta: Long
) {

    companion object {
        fun fromTransacao(transacao:Transacao): TransacaoKafka {
            return TransacaoKafka(
                operacao = transacao.operacao,
                valor = transacao.valor,
                dataTransacao = transacao.dataTransacao,
                idCliente = transacao.idCliente,
                idConta = transacao.idConta
            )
        }
    }
}
