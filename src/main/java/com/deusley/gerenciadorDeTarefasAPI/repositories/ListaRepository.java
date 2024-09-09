package com.deusley.gerenciadorDeTarefasAPI.repositories;

import com.deusley.gerenciadorDeTarefasAPI.domain.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {
}
