package com.gcsistemas.vendasadmin.util;

public class Ambiente {

  public static AmbienteEnum getAmbiente() {
    String testeAmbiente = PropriedadesAplicacao.getInstance().getAmbiente();

    return AmbienteEnum.toEnum(testeAmbiente);

  }

}
