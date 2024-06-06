package com.gcsistemas.vendasadmin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.vendasadmin.model.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

  List<Fornecedor> findAllByOrderByNomeAsc();

  boolean findByNome(String nome);

  boolean findNotaEntradaById(Long id);

}
