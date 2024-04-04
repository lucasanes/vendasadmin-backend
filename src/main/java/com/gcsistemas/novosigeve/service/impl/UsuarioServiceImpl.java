package com.gcsistemas.novosigeve.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcsistemas.novosigeve.exception.ErroAutenticacao;
import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Usuario;
import com.gcsistemas.novosigeve.model.repository.UsuarioRepository;
import com.gcsistemas.novosigeve.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado para o email informado.");
		}
		
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		
		return usuario.get();
	}

	
	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}
	
	private void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		
		if (existe)
			throw new RegraNegocioException("Já existe um usuário cadastrado com esse email.");
	}

	@Override
	@Transactional
	public Usuario alterarSenha(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		return repository.save(usuario);
	}

}
