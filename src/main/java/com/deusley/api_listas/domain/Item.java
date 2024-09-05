package com.deusley.api_listas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String titulo;
    private boolean destacado;
    private boolean concluido;

    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

}
