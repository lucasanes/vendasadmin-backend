package com.gcsistemas.novosigeve.service;

import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.Unidade;

public interface UnidadeService {

	List<Unidade> buscaTodosRegistros();
	
	Optional<Unidade> buscaRegistro(Long id);
	
	Unidade salvar(Unidade unidade);
	
	void excluir(Unidade unidade);
	
}
