package com.gcsistemas.vendasadmin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcsistemas.vendasadmin.model.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

  List<Empresa> findAllByOrderByNomeAsc();

  List<Empresa> findByNome(String nome);

  List<Empresa> findByNomeContainingIgnoreCase(String nome);

}
