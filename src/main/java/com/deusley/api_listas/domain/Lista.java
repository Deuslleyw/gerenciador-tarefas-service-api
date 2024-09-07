package com.deusley.api_listas.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lista_id")
    private List<Item> itens;















}
