package com.davi.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davi.teste.exception.ResourceNotFoundException;
import com.davi.teste.model.Conta;
import com.davi.teste.repository.ContaRepository;

@Service
public class ContaService {
    private ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta buscarPorNome(String titular) {
        Conta conta = repository.findByTitular(titular);
        return conta;
    }

    public Conta salvarConta(Conta conta) {
        return repository.save(conta);
    }

    public List<Conta> buscarContas() {
        return repository.findAll();
    }

    public Conta buscarPorID(Long id) {
        Optional<Conta> conta = repository.findById(id);
        return conta.orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));
    }

    public Conta updatePorID(Conta contaUpdate, Long id) {
        Optional<Conta> contaOptional = repository.findById(id);
        Conta conta = contaOptional.orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        conta.setNumero(contaUpdate.getNumero());
        conta.setSaldo(contaUpdate.getSaldo());
        conta.setTitular(contaUpdate.getTitular());
        return repository.save(conta);
    }

    public void deletePorID(Long id) {
        Optional<Conta> contaDelete = repository.findById(id);
        Conta conta = contaDelete.orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));
        repository.delete(conta);
    }
}
