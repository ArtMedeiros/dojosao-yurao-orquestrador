package br.com.zup.orquestrador.recarga.celular

import br.com.zup.orquestrador.common.banco.Transacao
import br.com.zup.orquestrador.common.AfterProcess
import br.com.zup.orquestrador.common.Operacao
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDateTime

@RestController
@RequestMapping("/recarga-celular")
class RecargaCelularController(
    val acoes:List<AfterProcess>
) {

    //Testando uma operação de recarga de celular
    @PostMapping
    fun recargaCelular(): ResponseEntity<Any> {
        val transacao = Transacao(
            operacao = Operacao.RECARGA_CELULAR,
            valor = BigDecimal(25.0),
            dataTransacao = LocalDateTime.now(),
            idCliente = 1,
            idConta = 1
        )

        acoes.forEach{ it.processa(transacao) }

        return ResponseEntity.ok().build()
    }
}