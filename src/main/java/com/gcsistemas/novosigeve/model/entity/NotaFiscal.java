package com.gcsistemas.novosigeve.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gcsistemas.novosigeve.util.TipoNotaEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nota_fiscal", schema = "sigeve")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscal {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_nota")
	private Long numeroNota; 
	
	@Column(name = "data_nota")
	private Date dataNota;
	
	@Column(name = "tipo_nota")
	private TipoNotaEnum tipoNota;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "cancelada")
	private Boolean cancelada;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;
	
	@Column(name = "data_cancelamento")
	private Date dataCancelamento;

}
