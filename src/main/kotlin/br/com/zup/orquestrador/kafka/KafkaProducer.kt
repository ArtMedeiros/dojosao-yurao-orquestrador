package br.com.zup.orquestrador.kafka

import br.com.zup.orquestrador.common.banco.Transacao
import br.com.zup.orquestrador.common.AfterProcess
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, TransacaoKafka>
): AfterProcess {

    @Value("\${kafka.producer.topic}")
    private lateinit var topicName:String

    override fun processa(transacao: Transacao) {
        val transacaoKafka = TransacaoKafka.fromTransacao(transacao)

        kafkaTemplate.send(topicName, transacaoKafka)
    }
}