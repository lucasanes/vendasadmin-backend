package com.gcsistemas.vendasadmin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.vendasadmin.model.entity.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

  List<Unidade> findAllByOrderByDescricaoAsc();

  boolean findByDescricao(String descricao);

  boolean findProdutoById(Long id);

}
