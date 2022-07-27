package com.gcsistemas.novosigeve.service;

import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.Produto;

public interface ProdutoService {

	List<Produto> buscaTodosRegistros();
	
	Optional<Produto> buscaRegistro(Long id);
	
	Produto salvar(Produto produto);
	
	void excluir(Produto produto);
	
}
