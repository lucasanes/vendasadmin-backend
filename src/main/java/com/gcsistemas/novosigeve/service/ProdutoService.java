package com.gcsistemas.novosigeve.service;

import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.Produto;

public interface ProdutoService {

	List<Produto> buscaTodosRegistros();
	
	Optional<Produto> buscaRegistro(Long id);
	
	List<Produto> buscar(Produto filtro);
	
	Produto salvar(Produto produto);
	
	Produto atualizar(Produto produto);
	
	void excluir(Produto produto);
	
}
