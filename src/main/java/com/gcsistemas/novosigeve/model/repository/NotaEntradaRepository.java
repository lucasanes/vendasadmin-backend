package com.gcsistemas.novosigeve.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcsistemas.novosigeve.model.entity.NotaEntrada;

public interface NotaEntradaRepository extends JpaRepository<NotaEntrada, Long>{

	List<NotaEntrada> findByPeriodoOrderByEmpresaAscNumeroNotaAsc();

	List<NotaEntrada> findByFornecedorOrderByEmpresaAscNumeroNotaAsc();

	@Query("UPDATE ne SET ne.cancelada = 1, ne.dataCancelamento = ?2 FROM NotaEntrada ne WHERE ne.id = ?1")
	void cancelar(Long id, Date dataCancelamento);

}
