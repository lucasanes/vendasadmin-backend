package com.gcsistemas.novosigeve.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcsistemas.novosigeve.model.entity.NotaEntrada;
import com.gcsistemas.novosigeve.model.repository.NotaEntradaRepository;
import com.gcsistemas.novosigeve.service.NotaEntradaService;

public class NotaEntradaServiceImpl implements NotaEntradaService{

	@Autowired
	private NotaEntradaRepository repository;
	
	@Override
	public List<NotaEntrada> buscaPorPeriodo(Date dtInicio, Date dtFim) {
//		return repository.findByPeriodoOrderByEmpresaAscNumeroNotaAsc();
		return null;
	}

	@Override
	public List<NotaEntrada> buscaPorFornecedor(Long idFornecedor) {
//		return repository.findByFornecedorOrderByEmpresaAscNumeroNotaAsc();
		return null;
	}

	@Override
	public Optional<NotaEntrada> buscaRegistro(Long id) {
		return repository.findById(id);
	}
	
	@Override
	@Transactional
	public NotaEntrada salvar(NotaEntrada notaEntrada) {
		return repository.save(notaEntrada);
	}
	
	@Override
	@Transactional
	public NotaEntrada atualizar(NotaEntrada notaEntrada) {
		Objects.requireNonNull(notaEntrada.getId());
		return repository.save(notaEntrada);
	}

	@Override
	@Transactional
	public void cancelar(NotaEntrada notaEntrada) {
		Objects.requireNonNull(notaEntrada.getId());
		repository.cancelar(notaEntrada.getId(), new Date());
	}

}
