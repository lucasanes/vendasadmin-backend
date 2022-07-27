package com.gcsistemas.novosigeve.service;

import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.Empresa;

public interface EmpresaService {

	List<Empresa> buscaTodosRegistros();
	
	Optional<Empresa> buscaRegistro(Long id);
	
	Empresa salvar(Empresa empresa);
	
	void excluir(Empresa empresa);
	
}
