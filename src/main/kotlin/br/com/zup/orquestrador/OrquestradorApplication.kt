package br.com.zup.orquestrador

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
@EnableFeignClients
class OrquestradorApplication

fun main(args: Array<String>) {
	runApplication<OrquestradorApplication>(*args)
}
