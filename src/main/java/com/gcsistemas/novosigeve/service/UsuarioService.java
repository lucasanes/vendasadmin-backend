package com.gcsistemas.novosigeve.service;

import com.gcsistemas.novosigeve.model.entity.Usuario;

public interface UsuarioService {

		Usuario autenticar(String email, String senha);
		
		Usuario salvarUsuario(Usuario usuario);
		
		void validarEmail(String email);
}
