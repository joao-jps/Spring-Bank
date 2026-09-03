package io.github.joaojps.springbank.service;

import io.github.joaojps.springbank.model.Conta;
import io.github.joaojps.springbank.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criarConta(String nomeTitular, BigDecimal saldo) {

        Conta conta = new Conta(nomeTitular, saldo);
        conta.setNumeroConta(gerarNumeroConta());

        contaRepository.save(conta);
        return conta;
    }

    private String gerarNumeroConta() {
        Random random = new Random();
        int numeroConta = random.nextInt(100000, 1000000);
        return String.valueOf(numeroConta);
    }
}
