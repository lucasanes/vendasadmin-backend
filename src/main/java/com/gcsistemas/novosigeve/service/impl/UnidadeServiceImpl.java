package com.gcsistemas.novosigeve.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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
	public List<Unidade> buscar(Unidade filtro){
		return null;
	}
	
	@Override
	@Transactional
	public Unidade salvar(Unidade unidade) {
		validaDescricao(unidade);
		return repository.save(unidade);
	}

	@Override
	@Transactional
	public Unidade atualizar(Unidade unidade) {
		Objects.requireNonNull(unidade.getId());
		return repository.save(unidade);
	}
	
	@Override
	@Transactional
	public void excluir(Unidade unidade) {
		Objects.requireNonNull(unidade.getId());
		validaExistenciaProduto(unidade);
		repository.delete(unidade);
	}

	private void validaDescricao(Unidade unidade) {
		if (repository.findByDescricao(unidade.getDescricao())) {
			throw new RegraNegocioException("Já existe unidade cadastrada com a descrição informada.");
		}
	}
	
	private void validaExistenciaProduto(Unidade unidade) {
		if (repository.findProdutoById(unidade.getId())) {
			throw new RegraNegocioException("Esta unidade já possui produto associado. Não é possível excluí-la.");
		}
	}
	
}
