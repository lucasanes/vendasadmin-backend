package com.gcsistemas.novosigeve.dto;

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
	private Long usuario;
}
