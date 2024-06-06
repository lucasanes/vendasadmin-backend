package com.gcsistemas.vendasadmin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.vendasadmin.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  List<Produto> findAllByOrderByDescricaoAsc();

  boolean findByDescricao(String descricao);

  boolean findItemNotaEntradaById(Long id);

}
