package br.com.zup.orquestrador.recarga.celular

import br.com.zup.orquestrador.common.banco.Transacao
import br.com.zup.orquestrador.common.AfterProcess
import br.com.zup.orquestrador.common.Operacao
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/{idCliente}/{idConta}/recarga-celular")
class RecargaCelularController(
    val acoes:List<AfterProcess>,
    val recargaCelularClient: RecargaCelularClient
) {

    @PostMapping
    fun recargaCelular(
        @RequestBody @Valid request: RecargaRequest,
        @PathVariable idCliente:Long,
        @PathVariable idConta:Long
    ): ResponseEntity<Any> {
        return recargaCelularClient.recarga(request).run {
            if(this.statusCode != HttpStatus.OK)
                ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
            else {
                acoes.forEach{ it.processa(request.toTransacao(idCliente, idConta)) }

                ResponseEntity.ok().build()
            }
        }
    }
}