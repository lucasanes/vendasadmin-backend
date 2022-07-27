package com.gcsistemas.novosigeve.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Produto;
import com.gcsistemas.novosigeve.model.repository.ProdutoRepository;
import com.gcsistemas.novosigeve.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Override
	public List<Produto> buscaTodosRegistros() {

		return repository.findAllByOrderByDescricaoAsc();
	}

	@Override
	public Optional<Produto> buscaRegistro(Long id) {
		
		return repository.findById(id);
	}

	@Override
	public Produto salvar(Produto produto) {

		validaDescricao(produto);
		
		return repository.save(produto);
	}

	@Override
	public void excluir(Produto produto) {
		
		validaExistenciaItemNota(produto);
		
		repository.delete(produto);
		
	}

	private void validaDescricao(Produto produto) {
		
		if (repository.findByDescricao(produto.getDescricao())) {
			throw new RegraNegocioException("Já existe produto cadastrado com a descrição informada.");
		}
		
	}
	
	private void validaExistenciaItemNota(Produto produto) {
		
		if (repository.findItemNotaEntradaByProdutoId(produto.getId())) {
			throw new RegraNegocioException("Este produto já possui produto associado. Não é possível excluí-la.");
		}
		
	}
	
}
