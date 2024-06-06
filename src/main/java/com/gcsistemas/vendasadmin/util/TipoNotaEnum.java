package com.gcsistemas.vendasadmin.util;

public enum TipoNotaEnum {

  ENTRADA("ENTRADA", "Entrada"),

  SAIDA("SAIDA", "Saída");

  private final String sigla;

  private final String descricao;

  private TipoNotaEnum(String sigla, String descricao) {
    this.sigla = sigla;
    this.descricao = descricao;
  }

  public String getSigla() {
    return sigla;
  }

  public String getDescricao() {
    return descricao;
  }

}
