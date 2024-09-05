package com.deusley.api_listas.repositories;

import com.deusley.api_listas.domain.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaRepository extends JpaRepository<Lista, String> {
}
