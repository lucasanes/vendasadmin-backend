package com.gcsistemas.novosigeve.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.novosigeve.model.entity.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

	List<Produto> findAllByOrderByDescricaoAsc();

	boolean findByDescricao(String descricao);

	boolean findItemNotaEntradaById(Long id);

}
