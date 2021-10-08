package br.com.zup.orquestrador.common.banco

import br.com.zup.orquestrador.common.AfterProcess
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TransacaoProcess(
    val repository: TransacaoRepository
): AfterProcess {

    override fun processa(transacao: Transacao) {
        repository.save(transacao)
    }
}