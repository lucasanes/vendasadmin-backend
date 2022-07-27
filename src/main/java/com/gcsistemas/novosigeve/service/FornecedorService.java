package com.gcsistemas.novosigeve.service;

import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.Fornecedor;

public interface FornecedorService {

	List<Fornecedor> buscaTodosRegistros();
	
	Optional<Fornecedor> buscaRegistro(Long id);
	
	Fornecedor salvar(Fornecedor fornecedor);
	
	void excluir(Fornecedor fornecedor);
	
}
