package br.com.zup.orquestrador.recarga.celular

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "recargaCelular", url = "\${recarga.celular.host}")
interface RecargaCelularClient {

    @PostMapping("\${recarga.celular.recarga}")
    fun recarga(@RequestBody request: RecargaRequest): ResponseEntity<Any>
}