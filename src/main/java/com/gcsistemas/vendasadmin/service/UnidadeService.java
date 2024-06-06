package com.gcsistemas.vendasadmin.service;

import java.util.List;
import java.util.Optional;

import com.gcsistemas.vendasadmin.model.entity.Unidade;

public interface UnidadeService {

  List<Unidade> buscaTodosRegistros();

  Optional<Unidade> buscaRegistro(Long id);

  List<Unidade> buscar(Unidade filtro);

  Unidade salvar(Unidade unidade);

  Unidade atualizar(Unidade unidade);

  void excluir(Unidade unidade);

}
