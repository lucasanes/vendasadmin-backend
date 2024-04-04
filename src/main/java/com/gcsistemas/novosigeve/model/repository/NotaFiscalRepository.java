package com.gcsistemas.novosigeve.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcsistemas.novosigeve.model.entity.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long>{

//	List<NotaEntrada> findByPeriodoOrderByEmpresaAscNumeroNotaAsc();

//	List<NotaEntrada> findByFornecedorOrderByEmpresaAscNumeroNotaAsc();

	@Query("UPDATE NotaFiscal SET cancelada = 1, dataCancelamento = ?2 WHERE id = ?1")
	void cancelar(Long id, Date dataCancelamento);

	@Query("SELECT 1 FROM NotaFiscal WHERE empresa.id = ?1")
	NotaFiscal findNotaEntradaByIdEmpresa(Long idEmpresa);
}
