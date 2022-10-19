package com.gcsistemas.novosigeve.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gcsistemas.novosigeve.model.entity.NotaFiscal;

public interface NotaEntradaService {

	List<NotaFiscal> buscaPorPeriodo(Date dtInicio, Date dtFim);
	
	List<NotaFiscal> buscaPorFornecedor(Long idFornecedor);
	
	Optional<NotaFiscal> buscaRegistro(Long id);
	
	NotaFiscal salvar(NotaFiscal notaEntrada);
	
	NotaFiscal atualizar(NotaFiscal notaEntrada);
	
	void cancelar(NotaFiscal notaEntrada);
	
}
