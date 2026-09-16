package io.github.joaojps.springbank.controller;

import io.github.joaojps.springbank.dto.TransferenciaRequestDTO;
import io.github.joaojps.springbank.dto.TransferenciaResponseDTO;
import io.github.joaojps.springbank.service.TransferenciaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController (TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    //Endpoint de Transferência
    @PostMapping
    public TransferenciaResponseDTO transferir(@RequestBody TransferenciaRequestDTO dto) { // TransferenciaRespondeDTO para n expor dados sensiveis da conta destino
        return transferenciaService.transferir(
                dto.getNumeroContaOrigem(),
                dto.getValor(),
                dto.getNumeroContaDestino()
        );
    }


}
