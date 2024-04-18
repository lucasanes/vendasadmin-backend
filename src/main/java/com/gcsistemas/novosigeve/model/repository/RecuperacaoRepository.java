package com.gcsistemas.novosigeve.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.novosigeve.model.entity.Recuperacao;

public interface RecuperacaoRepository extends JpaRepository<Recuperacao, Long> {

  Optional<Recuperacao> findByCodigo(String codigo);

}
