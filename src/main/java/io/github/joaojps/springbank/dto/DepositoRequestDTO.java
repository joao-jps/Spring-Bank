package io.github.joaojps.springbank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositoRequestDTO {
    private BigDecimal valor;

}
