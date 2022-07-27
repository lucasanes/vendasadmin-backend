package com.gcsistemas.novosigeve.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "item_nota_entrada", schema = "sigeve")
@Data
public class ItemNotaEntrada {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_nota_entrada")
	private NotaEntrada notaEntrada;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@Column(name = "qtd")
	private BigDecimal qtd;
	
	@Column(name = "preco")
	private BigDecimal preco;
	
	@Column(name = "total")
	private BigDecimal total;

}
