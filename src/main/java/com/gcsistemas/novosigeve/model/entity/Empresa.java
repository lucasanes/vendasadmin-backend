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
@Table(name = "empresa", schema = "sigeve")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf_cnpj")
  private String cpfCnpj;

  @Column(name = "email")
  private String email;

  @Column(name = "telefone")
  private String telefone;

  @Column(name = "inscricao")
  private String inscricao;

  @Column(name = "cep")
  private String cep;

  @Column(name = "endereco")
  private String endereco;

  @Column(name = "uf")
  private String uf;
  @Column(name = "cidade")
  private String cidade;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "observacao")
  private String observacao;

  @Column(name = "data_cadastro")
  private Date dataCadastro;

  @Column(name = "proximo_numero_nota")
  private Long proximoNumeroNota;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;
}