package com.gcsistemas.novosigeve.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Unidade;
import com.gcsistemas.novosigeve.model.repository.UnidadeRepository;
import com.gcsistemas.novosigeve.service.UnidadeService;

@Service
public class UnidadeServiceImpl implements UnidadeService {

	@Autowired
	private UnidadeRepository repository;
	
	@Override
	public List<Unidade> buscaTodosRegistros() {

		return repository.findAllByOrderByDescricaoAsc();
	}

	@Override
	public Optional<Unidade> buscaRegistro(Long id) {
		
		return repository.findById(id);
	}

	@Override
	public Unidade salvar(Unidade unidade) {

		validaDescricao(unidade);
		
		return repository.save(unidade);
	}

	@Override
	public void excluir(Unidade unidade) {
		
		validaExistenciaProduto(unidade);
		
		repository.delete(unidade);
		
	}

	private void validaDescricao(Unidade unidade) {
		
		if (repository.findByDescricao(unidade.getDescricao())) {
			throw new RegraNegocioException("Já existe unidade cadastrada com a descrição informada.");
		}
		
	}
	
	private void validaExistenciaProduto(Unidade unidade) {
		
		if (repository.findProdutoByUnidadeId(unidade.getId())) {
			throw new RegraNegocioException("Esta unidade já possui produto associado. Não é possível excluí-la.");
		}
		
	}
	
}
