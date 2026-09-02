package io.github.joaojps.springbank.repository;

import io.github.joaojps.springbank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
