package br.com.zup.orquestrador.common.banco

import br.com.zup.orquestrador.common.Operacao
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
class Transacao(
    @Enumerated(value = STRING)
    val operacao: Operacao,
    val valor: BigDecimal,
    val dataTransacao: LocalDateTime,
    val idCliente: Long,
    val idConta: Long
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id:Long? = null

    var status:TransacaoStatus = TransacaoStatus.CRIADA
        private set

    fun finalizarTransacao() {
        this.status = TransacaoStatus.FINALIZADA
    }
}
