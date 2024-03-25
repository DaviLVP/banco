package com.davi.teste.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Titular {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String sexo;
    private String nome;
    private double idade;
    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "conta_id")
    private Conta conta;
}
