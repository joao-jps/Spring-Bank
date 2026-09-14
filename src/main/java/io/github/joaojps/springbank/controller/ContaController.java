package io.github.joaojps.springbank.controller;

import io.github.joaojps.springbank.dto.ContaRequestDTO;
import io.github.joaojps.springbank.dto.DepositoRequestDTO;
import io.github.joaojps.springbank.model.Conta;
import io.github.joaojps.springbank.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Recebe requisições HTTP e devolve dados (Geralmente JSON)
@RequestMapping("/api/v1/contas") // Rota comum para todos os métodos da classe
public class ContaController {

    // Injetando Dependência para poder conversar com Conta Service e usar seus métodos
    private final ContaService contaService;

    public ContaController(ContaService contaService){
        this.contaService = contaService;
    }

    // Endpoint para criar uma conta nova
    @PostMapping
    public Conta criarConta(@RequestBody ContaRequestDTO dto) {
        return contaService.criarConta(dto.getNomeTitular(), dto.getSaldo());
    }

    // Endpoint para procurar conta específica por numero da conta específico
    @GetMapping("/{numeroConta}") // Captura o valor (id) q passamos e busca no sistema pelo id específico
    public Conta buscarPorNumeroConta(@PathVariable String numeroConta){
        return contaService.buscarPorNumeroConta(numeroConta);
    }

    @GetMapping // Endpoint para retornar todas as contas do banco como json
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    // Endpoint para o depósito entre contas
    @PostMapping("/{numeroConta}/depositar")
    public Conta depositar(@PathVariable String numeroConta, @RequestBody DepositoRequestDTO dto) {
        return contaService.depositar(numeroConta, dto.getValor());
    }




}
