package br.com.zup.orquestrador.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig {

    @Value("\${kafka.producer.boostrap.server}")
    private lateinit var boostrapServer:String

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, TransacaoKafka> {
        val props = mapOf(
            Pair(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer),
            Pair(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java),
            Pair(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
        )

        return KafkaTemplate(DefaultKafkaProducerFactory(props))
    }
}