package io.github.joaojps.springbank.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas")   // Cria uma tabela no banco de dados chamada contas
@Getter   // Cria automaticamento todos os Getters
@NoArgsConstructor // Cria um construtor vazio obrigatorio pedido pelo JPA
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)  // Faz com que// todo numero da Conta tenha q ser unico
    @Setter
    private String numeroConta;

    @Column(nullable = false)
    @Setter
    private String nomeTitular;

    @Column(precision = 15, scale = 2)  // Máximo de 15 numeros, 2 numeros após a virgula
    private BigDecimal saldo;

    @Column(updatable = false, nullable = false) // Não pode ser nulo e não pode ser atualizado
    private LocalDateTime dataCriacao;

    @Column(nullable = true)
    @Setter
    private LocalDateTime dataEncerramento;

    @Enumerated(EnumType.STRING) // Faz com que o status seja tratado como STRING e não como Numero
    @Column(nullable = false)
    @Setter
    private StatusConta status;

    public Conta(String nomeTitular, BigDecimal saldo) {  // Construtor que pede e contem as informações necessária pra criar uma conta
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusConta.ATIVA;
    }


}
