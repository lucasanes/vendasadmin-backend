package com.gcsistemas.novosigeve.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Empresa;
import com.gcsistemas.novosigeve.model.repository.EmpresaRepository;
import com.gcsistemas.novosigeve.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	@Override
	public List<Empresa> buscaTodosRegistros() {

		return repository.findAllByOrderByNomeAsc();
	}

	@Override
	public Optional<Empresa> buscaRegistro(Long id) {
		
		return repository.findById(id);
	}

	@Override
	public Empresa salvar(Empresa empresa) {
		
		validaNome(empresa);
		
		return repository.save(empresa);
	}

	@Override
	public void excluir(Empresa empresa) {
		
		validaExistenciaNota(empresa);
		
		repository.delete(empresa);
		
	}

	private void validaNome(Empresa empresa) {
		
		if (repository.findByNome(empresa.getNome())) {
			throw new RegraNegocioException("Já existe empresa cadastrada com o nome informado.");
		}
		
	}
	
	private void validaExistenciaNota(Empresa empresa) {
		
		if (repository.findNotaEntradaByEmpresaId(empresa.getId())) {
			throw new RegraNegocioException("Esta empresa já possui notas associadas. Não é possível excluí-la.");
		}
		
	}
	
}
