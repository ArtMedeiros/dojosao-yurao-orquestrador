package br.com.zup.orquestrador.conta.digital

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("\${conta.digital.host}")
interface ContaDigitalClient {

}