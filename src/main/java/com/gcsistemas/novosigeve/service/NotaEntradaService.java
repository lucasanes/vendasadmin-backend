package com.gcsistemas.novosigeve.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.NotaEntrada;

public interface NotaEntradaService {

	List<NotaEntrada> buscaPorPeriodo(Date dtInicio, Date dtFim);
	
	List<NotaEntrada> buscaPorFornecedor(Long idFornecedor);
	
	Optional<NotaEntrada> buscaRegistro(Long id);
	
	NotaEntrada salvar(NotaEntrada notaEntrada);
	
	void cancelar(NotaEntrada notaEntrada);
	
}
