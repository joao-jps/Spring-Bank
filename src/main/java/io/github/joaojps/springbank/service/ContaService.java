package io.github.joaojps.springbank.service;

import io.github.joaojps.springbank.model.Conta;
import io.github.joaojps.springbank.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service // Essa classe faz as funcionalidades da aplicação
public class ContaService {

    // Conecta a depêndencia ContaRepository para poder conversar com banco de dados
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criarConta(String nomeTitular, BigDecimal saldo) {

        Conta conta = new Conta(nomeTitular, saldo);
        conta.setNumeroConta(gerarNumeroConta());

        contaRepository.save(conta); //Salva a conta criada no banco de dados por meio da conversa JPA presente no ContaRepository
        return conta;
    }

    private String gerarNumeroConta() {
        Random random = new Random();
        int numeroConta = random.nextInt(100000, 1000000); // Gera numero identificador de conta de 6 dígitos
        return String.valueOf(numeroConta);
    }
}
