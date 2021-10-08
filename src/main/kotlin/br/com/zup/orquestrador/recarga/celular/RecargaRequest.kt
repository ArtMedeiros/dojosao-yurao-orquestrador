package br.com.zup.orquestrador.recarga.celular

import br.com.zup.orquestrador.common.Operacao
import br.com.zup.orquestrador.common.banco.Transacao
import com.sun.istack.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class RecargaRequest(
    @field:NotBlank
    val numCelular: String,

    @field:NotNull
    val valorRecarga: BigDecimal,

    @field:NotNull
    val operadora: Operadora
) {

    fun toTransacao(idCliente:Long, idConta:Long): Transacao {
        return Transacao(
            operacao = Operacao.RECARGA_CELULAR,
            valor = valorRecarga,
            dataTransacao = LocalDateTime.now(),
            idCliente = idCliente,
            idConta = idConta
        )
    }
}