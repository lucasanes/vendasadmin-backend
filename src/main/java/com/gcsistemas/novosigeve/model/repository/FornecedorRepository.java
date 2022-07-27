package com.gcsistemas.novosigeve.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.novosigeve.model.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	List<Fornecedor> findAllByOrderByNomeAsc();

	boolean findByNome(String nome);

	boolean findNotaEntradaByFornecedorId(Long id);

}
