package io.github.joaojps.springbank.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas")
@Getter
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Setter
    private String numeroConta;

    @Column(nullable = false)
    @Setter
    private String nomeTitular;

    @Column(precision = 15, scale = 2)
    private BigDecimal saldo;

    @Column(updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = true)
    @Setter
    private LocalDateTime dataEncerramento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private StatusConta status;

    public Conta(String nomeTitular, BigDecimal saldo) {
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusConta.ATIVA;
    }


}
