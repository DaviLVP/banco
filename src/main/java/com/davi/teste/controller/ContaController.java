package com.davi.teste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davi.teste.exception.ResourceNotFoundException;
import com.davi.teste.model.Conta;
import com.davi.teste.service.ContaService;

@RestController
public class ContaController {
  private ContaService service;

  public ContaController(ContaService service) {
    this.service = service;
  }

  @GetMapping("/contas/{id}")
  public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorID(id));
  }

  @GetMapping("/contas")
  public ResponseEntity<List<Conta>> buscarContas(@RequestParam(required = false) String nome) {
    if (nome != null) {
      return ResponseEntity.ok(List.of(service.buscarPorNome(nome)));
    }
    return ResponseEntity.ok(service.buscarContas());
  }

  @PostMapping("/contas")
  public ResponseEntity<Conta> salvaConta(@RequestBody Conta conta) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarConta(conta));
  }

  // @GetMapping("/contas/{id}")
  // public ResponseEntity<Conta> buscarContaID(@PathVariable Long id) {
  // Conta retorno = null;
  // try {
  // retorno = service.buscarPorID(id);
  // return ResponseEntity.ok(retorno);
  // } catch (ResourceNotFoundException e) {
  // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
  // }
  // }

  @PutMapping("/contas/{id}")
  public ResponseEntity<Conta> updateContaID(@PathVariable Long id, @RequestBody Conta contaUpdate) {
    try {
      return ResponseEntity.ok(service.updatePorID(contaUpdate, id));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @DeleteMapping("/contas/{id}")
  public ResponseEntity<Void> deleteContaID(@PathVariable Long id) {
    try {
      service.deletePorID(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

}
