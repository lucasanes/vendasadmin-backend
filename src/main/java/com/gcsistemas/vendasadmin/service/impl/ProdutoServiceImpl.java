package com.gcsistemas.vendasadmin.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcsistemas.vendasadmin.exception.RegraNegocioException;
import com.gcsistemas.vendasadmin.model.entity.Produto;
import com.gcsistemas.vendasadmin.model.repository.ProdutoRepository;
import com.gcsistemas.vendasadmin.service.ProdutoService;

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
  public List<Produto> buscar(Produto filtro) {
    return null;
  }

  @Override
  @Transactional
  public Produto salvar(Produto produto) {
    validaDescricao(produto);
    return repository.save(produto);
  }

  @Override
  @Transactional
  public Produto atualizar(Produto produto) {
    Objects.requireNonNull(produto.getId());
    return repository.save(produto);
  }

  @Override
  @Transactional
  public void excluir(Produto produto) {
    Objects.requireNonNull(produto.getId());
    validaExistenciaItemNota(produto);
    repository.delete(produto);

  }

  private void validaDescricao(Produto produto) {
    if (repository.findByDescricao(produto.getDescricao())) {
      throw new RegraNegocioException("Já existe produto cadastrado com a descrição informada.");
    }
  }

  private void validaExistenciaItemNota(Produto produto) {
    if (repository.findItemNotaEntradaById(produto.getId())) {
      throw new RegraNegocioException("Este produto já possui produto associado. Não é possível excluí-la.");
    }
  }

}
