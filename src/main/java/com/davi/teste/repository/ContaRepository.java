package com.davi.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davi.teste.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    public Conta findByTitular(String titular);
}
