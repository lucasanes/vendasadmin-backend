package com.gcsistemas.vendasadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

  private Long id;
  private String nome;
  private String cpfCnpj;
  private String email;
  private String telefone;
  private String inscricao;
  private String cep;
  private String endereco;
  private String uf;
  private String cidade;
  private String bairro;
  private String observacao;
  private Long proximoNumeroNota;
  private Long usuarioId;
}
