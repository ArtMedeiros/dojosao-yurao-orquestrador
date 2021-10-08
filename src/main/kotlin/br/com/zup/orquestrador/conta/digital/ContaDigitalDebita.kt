package br.com.zup.orquestrador.conta.digital

import br.com.zup.orquestrador.common.AfterProcess
import br.com.zup.orquestrador.common.banco.TransacaoRepository
import br.com.zup.orquestrador.common.banco.TransacaoStatus
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ContaDigitalDebita(
    val contaDigitalClient: ContaDigitalClient,
    val repository: TransacaoRepository,
    val acoes: List<AfterProcess>
) {

    @Scheduled(fixedDelay = 5000) //Executa a cada 5 minutos
    fun debitarTransacoes() {
        repository.findByStatus(TransacaoStatus.CRIADA).let { transacoes ->
            transacoes.forEach { transacao ->
                contaDigitalClient.debita(ContaDigitalRequest.fromTransacao(transacao)).let { response ->
                    if (response.statusCode == HttpStatus.OK) {
                        transacao.finalizarTransacao()
                        acoes.forEach { it.processa(transacao) }
                    }
                }
            }
        }
    }
}