package com.deusley.gerenciadorDeTarefasAPI.domain;

import com.deusley.gerenciadorDeTarefasAPI.enums.Estado;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;
    private String titulo;

    private Estado estado;

    private boolean destacado;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Tarefa tarefa;

}
