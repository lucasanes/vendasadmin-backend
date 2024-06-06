package com.gcsistemas.vendasadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

  private Long id;
  private String nome;
  private String email;
  private String senha;
}
