package br.com.zup.orquestrador.conta.digital

import br.com.zup.orquestrador.common.banco.Transacao
import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class ContaDigitalRequest(
    @field:NotNull
    val idConta:Long,

    @field:NotNull
    val valor:BigDecimal
) {
    companion object {
        fun fromTransacao(transacao: Transacao): ContaDigitalRequest {
            return ContaDigitalRequest(
                idConta = transacao.idConta,
                valor = transacao.valor
            )
        }
    }
}