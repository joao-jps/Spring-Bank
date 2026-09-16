package io.github.joaojps.springbank.service;

import io.github.joaojps.springbank.dto.TransferenciaResponseDTO;
import io.github.joaojps.springbank.model.Conta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferenciaService {

    private final ContaService contaService;

    public TransferenciaService(ContaService contaService) {
        this.contaService = contaService;
    }

    @Transactional
    public TransferenciaResponseDTO transferir(String numeroContaOrigem, BigDecimal valor, String numeroContaDestino) {
        Conta contaOrigem = contaService.buscarPorNumeroConta(numeroContaOrigem);
        Conta contaDestino = contaService.buscarPorNumeroConta(numeroContaDestino);

        //operação de débito primeiro para evitar problemas de saldo insuficiente
        contaOrigem.debitar(valor);
        contaDestino.creditar(valor);

        contaService.salvar(contaOrigem);  // Debita o dinheiro da conta de quem transfere
        contaService.salvar(contaDestino);  // Credita o dinheiro na conta de quem recebe a transferência

        //Retorno para quem faz a transferência
        TransferenciaResponseDTO resposta = new TransferenciaResponseDTO();
        resposta.setMensagem("Transfêrencia realizada com sucesso!");
        resposta.setNumeroConta(contaOrigem.getNumeroConta());
        resposta.setSaldoAtual(contaOrigem.getSaldo());

        return resposta;
    }
}
