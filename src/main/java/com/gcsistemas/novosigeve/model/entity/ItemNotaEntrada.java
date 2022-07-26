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

@Entity
@Table(name = "item_nota_entrada", schema = "sigeve")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotaEntrada getNotaEntrada() {
		return notaEntrada;
	}

	public void setNotaEntrada(NotaEntrada notaEntrada) {
		this.notaEntrada = notaEntrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
