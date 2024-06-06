package com.gcsistemas.vendasadmin.dto;

import com.gcsistemas.vendasadmin.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
  private String token;
  private Usuario user;

}
