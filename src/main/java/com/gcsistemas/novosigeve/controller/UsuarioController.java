package com.gcsistemas.novosigeve.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcsistemas.novosigeve.dto.AuthResponse;
import com.gcsistemas.novosigeve.dto.TokenResponse;
import com.gcsistemas.novosigeve.dto.UsuarioDTO;
import com.gcsistemas.novosigeve.exception.ErroAutenticacao;
import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Usuario;
import com.gcsistemas.novosigeve.service.UsuarioService;
import com.gcsistemas.novosigeve.util.JwtUtil;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UsuarioService usuarioService;

  @SuppressWarnings("rawtypes")
  @PostMapping("/salvar")
  public ResponseEntity salvar(@RequestBody UsuarioDTO request) {
    Usuario usuario = Usuario.builder()
        .nome(request.getNome())
        .email(request.getEmail())
        .senha(request.getSenha()).build();

    try {
      usuario.setDataCadastro(new Date());
      Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
      return ResponseEntity.ok(usuarioSalvo);
    } catch (RegraNegocioException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @PutMapping("/alterarSenha/{id}")
  public ResponseEntity alterarSenha(@PathVariable("id") Long id, @RequestBody UsuarioDTO request) {
    return usuarioService.findById(id).map(entity -> {
      try {
        Usuario entidade = converter(request);
        entidade.setId(entity.getId());
        usuarioService.alterarSenha(entidade);
        return ResponseEntity.ok(entidade);
      } catch (RegraNegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }).orElseGet(() -> new ResponseEntity("Usuário não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @PostMapping("/autenticar")
  public ResponseEntity autenticar(@RequestBody UsuarioDTO request) {

    try {
      Usuario usuarioAutenticado = usuarioService.autenticar(request.getEmail(), request.getSenha());
      String token = jwtUtil.generateToken(usuarioAutenticado);

      AuthResponse response = new AuthResponse(token, usuarioAutenticado);

      return new ResponseEntity(response, HttpStatus.OK);
    } catch (ErroAutenticacao e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @GetMapping("/validar/{token}")
  public ResponseEntity validar(@PathVariable("token") String token) {

    try {
      Boolean tokenIsValid = jwtUtil.validateToken(token);

      Usuario usuario = jwtUtil.extractUsuario(token);

      TokenResponse response = new TokenResponse(tokenIsValid, usuario);

      return new ResponseEntity(response, HttpStatus.OK);
    } catch (Error e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  private Usuario converter(UsuarioDTO dto) {
    Usuario converter = new Usuario();
    converter.setId(dto.getId());
    converter.setNome(dto.getNome());
    converter.setEmail(dto.getEmail());
    converter.setSenha(dto.getSenha());

    return converter;
  }

}
