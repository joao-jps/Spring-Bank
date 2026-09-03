package io.github.joaojps.springbank.controller;

import io.github.joaojps.springbank.dto.ContaRequestDTO;
import io.github.joaojps.springbank.model.Conta;
import io.github.joaojps.springbank.service.ContaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Recebe requisições HTTP e devolve dados (Geralmente JSON)
@RequestMapping("/contas") // Rota comum para todos os métodos da classe
public class ContaController {

    // Injetando Dependência para poder conversar com Conta Service e usar seus métodos
    private final ContaService contaService;

    public ContaController(ContaService contaService){
        this.contaService = contaService;
    }

    @PostMapping
    public Conta criarConta(@RequestBody ContaRequestDTO dto) {
        return contaService.criarConta(dto.getNomeTitular(), dto.getSaldo());
    }

}
