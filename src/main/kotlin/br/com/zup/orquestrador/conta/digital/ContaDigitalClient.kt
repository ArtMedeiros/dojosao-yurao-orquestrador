package br.com.zup.orquestrador.conta.digital

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.validation.Valid

@FeignClient(name="contaDigital", url="\${conta.digital.host}")
interface ContaDigitalClient {

    @GetMapping("/api/conta/{idConta}")
    fun verificaSaldo(@PathVariable idConta:Long, @RequestParam valor:BigDecimal): ResponseEntity<Any>

    @PostMapping("/api/conta/debita")
    fun debita(@RequestBody @Valid request: ContaDigitalRequest) : ResponseEntity<Any>
}