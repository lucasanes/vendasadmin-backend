package com.gcsistemas.vendasadmin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcsistemas.vendasadmin.model.entity.NotaFiscal;
import com.gcsistemas.vendasadmin.model.repository.NotaFiscalRepository;
import com.gcsistemas.vendasadmin.service.NotaEntradaService;

public class NotaEntradaServiceImpl implements NotaEntradaService {

  @Autowired
  private NotaFiscalRepository repository;

  @Override
  public List<NotaFiscal> buscaPorPeriodo(Date dtInicio, Date dtFim) {
    // return repository.findByPeriodoOrderByEmpresaAscNumeroNotaAsc();
    return null;
  }

  @Override
  public List<NotaFiscal> buscaPorFornecedor(Long idFornecedor) {
    // return repository.findByFornecedorOrderByEmpresaAscNumeroNotaAsc();
    return null;
  }

  @Override
  public Optional<NotaFiscal> buscaRegistro(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public NotaFiscal salvar(NotaFiscal notaEntrada) {
    return repository.save(notaEntrada);
  }

  @Override
  @Transactional
  public NotaFiscal atualizar(NotaFiscal notaEntrada) {
    Objects.requireNonNull(notaEntrada.getId());
    return repository.save(notaEntrada);
  }

  @Override
  @Transactional
  public void cancelar(NotaFiscal notaEntrada) {
    Objects.requireNonNull(notaEntrada.getId());
    repository.cancelar(notaEntrada.getId(), new Date());
  }

}
