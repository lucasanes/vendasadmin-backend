package com.gcsistemas.vendasadmin.model.entity;

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
@Table(name = "fornecedor", schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf_cnpj")
  private String cpfCnpj;

  @Column(name = "inscricao")
  private String inscricao;

  @Column(name = "endereco")
  private String endereco;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "cep")
  private String cep;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "uf")
  private String uf;

  @Column(name = "telefone")
  private String telefone;

  @Column(name = "email")
  private String email;

  @Column(name = "observacao")
  private String observacao;

  @Column(name = "data_cadastro")
  private Date dataCadastro;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

}