package com.gcsistemas.novosigeve.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Fornecedor;
import com.gcsistemas.novosigeve.model.repository.FornecedorRepository;
import com.gcsistemas.novosigeve.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	
	@Override
	public List<Fornecedor> buscaTodosRegistros() {
		return repository.findAllByOrderByNomeAsc();
	}

	@Override
	public Optional<Fornecedor> buscaRegistro(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Fornecedor> buscar(Fornecedor filtro){
		return null;
	}
	
	@Override
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		validaNome(fornecedor);
		return repository.save(fornecedor);
	}

	@Override
	@Transactional
	public Fornecedor atualizar(Fornecedor fornecedor) {
		Objects.requireNonNull(fornecedor.getId());
		return repository.save(fornecedor);
	}
	
	@Override
	@Transactional
	public void excluir(Fornecedor fornecedor) {
		Objects.requireNonNull(fornecedor.getId());
		validaExistenciaNota(fornecedor);
		repository.delete(fornecedor);
	}

	private void validaNome(Fornecedor fornecedor) {
		if (repository.findByNome(fornecedor.getNome())) {
			throw new RegraNegocioException("Já existe fornecedor cadastrado com o nome informado.");
		}
	}
	
	private void validaExistenciaNota(Fornecedor fornecedor) {
		if (repository.findNotaEntradaById(fornecedor.getId())) {
			throw new RegraNegocioException("Este fornecedor já possui notas associadas. Não é possível excluí-lo.");
		}
	}
	
}
