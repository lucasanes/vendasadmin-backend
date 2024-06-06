package com.gcsistemas.vendasadmin.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.vendasadmin.model.entity.Recuperacao;

public interface RecuperacaoRepository extends JpaRepository<Recuperacao, Long> {

  Optional<Recuperacao> findByCodigo(String codigo);

}
