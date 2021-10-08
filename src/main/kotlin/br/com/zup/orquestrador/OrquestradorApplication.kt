package br.com.zup.orquestrador

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableKafka
@EnableFeignClients
@EnableScheduling
class OrquestradorApplication

fun main(args: Array<String>) {
	runApplication<OrquestradorApplication>(*args)
}
