package com.deusley.gerenciadorDeTarefasAPI.repositories;

import com.deusley.gerenciadorDeTarefasAPI.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
