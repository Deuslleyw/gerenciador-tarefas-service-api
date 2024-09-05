package com.deusley.api_listas.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String nome;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lista_id")
    private Set<Item> itens;















}
