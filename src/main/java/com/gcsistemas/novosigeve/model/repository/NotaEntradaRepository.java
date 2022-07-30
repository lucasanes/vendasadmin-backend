package com.gcsistemas.novosigeve.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcsistemas.novosigeve.model.entity.NotaEntrada;

public interface NotaEntradaRepository extends JpaRepository<NotaEntrada, Long>{

//	List<NotaEntrada> findByPeriodoOrderByEmpresaAscNumeroNotaAsc();

//	List<NotaEntrada> findByFornecedorOrderByEmpresaAscNumeroNotaAsc();

	@Query("UPDATE NotaEntrada SET cancelada = 1, dataCancelamento = ?2 WHERE id = ?1")
	void cancelar(Long id, Date dataCancelamento);

}
