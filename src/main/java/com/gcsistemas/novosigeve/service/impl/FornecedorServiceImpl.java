package com.gcsistemas.novosigeve.service.impl;

import java.util.List;
import java.util.Optional;

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
	public Fornecedor salvar(Fornecedor fornecedor) {
		
		validaNome(fornecedor);
		
		return repository.save(fornecedor);
	}

	@Override
	public void excluir(Fornecedor fornecedor) {
		
		validaExistenciaNota(fornecedor);
		
		repository.delete(fornecedor);
		
	}

	private void validaNome(Fornecedor fornecedor) {
		
		if (repository.findByNome(fornecedor.getNome())) {
			throw new RegraNegocioException("Já existe fornecedor cadastrado com o nome informado.");
		}
		
	}
	
	private void validaExistenciaNota(Fornecedor fornecedor) {
		
		if (repository.findNotaEntradaByFornecedorId(fornecedor.getId())) {
			throw new RegraNegocioException("Este fornecedor já possui notas associadas. Não é possível excluí-lo.");
		}
		
	}
	
}
