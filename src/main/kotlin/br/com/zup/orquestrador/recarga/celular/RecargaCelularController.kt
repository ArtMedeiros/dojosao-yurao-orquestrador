package br.com.zup.orquestrador.recarga.celular

import br.com.zup.orquestrador.common.Operacao
import br.com.zup.orquestrador.common.banco.Transacao
import br.com.zup.orquestrador.common.banco.TransacaoProcess
import br.com.zup.orquestrador.conta.digital.ContaDigitalClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/{idCliente}/{idConta}/recarga-celular")
class RecargaCelularController(
    val recargaCelularClient: RecargaCelularClient,
    val contaDigitalClient: ContaDigitalClient,
    val transacaoProcess: TransacaoProcess
) {

    @PostMapping
    fun recargaCelular(
        @RequestBody @Valid request: RecargaRequest,
        @PathVariable idCliente:Long,
        @PathVariable idConta:Long
    ): ResponseEntity<Any> {
        val temSaldo = contaDigitalClient.verificaSaldo(idConta, request.valorRecarga).statusCode == HttpStatus.OK

        return if(!temSaldo){
            ResponseEntity.unprocessableEntity().build()
        } else{
            recargaCelularClient.recarga(request).run {
                if(this.statusCode != HttpStatus.OK)
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
                else {
                    transacaoProcess.processa(
                        Transacao(
                            operacao = Operacao.RECARGA_CELULAR,
                            valor = request.valorRecarga,
                            dataTransacao = LocalDateTime.now(),
                            idCliente = idCliente,
                            idConta = idConta
                    ))
                    ResponseEntity.ok().build()
                }
            }
        }
    }
}