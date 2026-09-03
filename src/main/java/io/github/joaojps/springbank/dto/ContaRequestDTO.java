package io.github.joaojps.springbank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContaRequestDTO { // Data Transfer Object, existe para o controller receber o JSON e convertelo em objeto java

    private String nomeTitular;
    private BigDecimal saldo;

}
