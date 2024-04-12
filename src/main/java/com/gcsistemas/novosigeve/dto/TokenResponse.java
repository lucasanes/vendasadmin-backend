package com.gcsistemas.novosigeve.dto;

import com.gcsistemas.novosigeve.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
  private Boolean token;
  private Usuario user;

}
