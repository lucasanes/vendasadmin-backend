package com.gcsistemas.novosigeve.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidade", schema = "sigeve")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unidade {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

}
