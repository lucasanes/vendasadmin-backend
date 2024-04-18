package com.gcsistemas.novosigeve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecuperacaoDTO {

  private String email;
  private String hora;
}
