package com.gcsistemas.vendasadmin.util;

public enum AmbienteEnum {

  DSV("DSV", "Desenvolvimento"),

  HML("HML", "Homologação"),

  LOCAL("LOCAL", "Local"),

  PRD("PRD", "Produção");

  private final String sigla;

  private final String descricao;

  private AmbienteEnum(String sigla, String descricao) {
    this.sigla = sigla;
    this.descricao = descricao;
  }

  public static AmbienteEnum toEnum(String extSigla) {
    if (extSigla == null) {
      return AmbienteEnum.LOCAL;
    }

    for (AmbienteEnum e : values()) {
      if (e.getSigla().equalsIgnoreCase(extSigla)) {
        return e;
      }
    }
    return null;
  }

  public String getSigla() {
    return sigla;
  }

  public String getDescricao() {
    return descricao;
  }

}
