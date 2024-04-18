package com.gcsistemas.novosigeve.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcsistemas.novosigeve.dto.RecuperacaoDTO;
import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Recuperacao;
import com.gcsistemas.novosigeve.model.entity.Usuario;
import com.gcsistemas.novosigeve.service.RecuperacaoService;
import com.gcsistemas.novosigeve.service.UsuarioService;

@RestController
@RequestMapping("/api/recuperacao")
public class RecuperacaoController {

  @Autowired
  private RecuperacaoService recuperacaoService;

  @Autowired
  private UsuarioService usuarioService;

  @SuppressWarnings("rawtypes")
  @PostMapping("/gerar")
  public ResponseEntity gerar(@RequestBody RecuperacaoDTO request) {

    StringBuilder codeBuilder = new StringBuilder();
    Random random = new Random();

    for (int i = 0; i < 6; i++) {
      if (i % 2 == 0) {
        // Generate a random letter (a-z)
        char letter = (char) (random.nextInt(26) + 'a');
        codeBuilder.append(letter);
      } else {
        // Generate a random number (0-9)
        int number = random.nextInt(10);
        codeBuilder.append(number);
      }
    }

    String code = codeBuilder.toString().toUpperCase();

    try {

      Usuario usuario = usuarioService.findByEmail(request.getEmail())
          .orElseThrow(() -> new RegraNegocioException("Email não encontrado."));

      Recuperacao recuperacao = Recuperacao.builder()
          .codigo(code)
          .usuario(usuario)
          .hora(request.getHora()).build();

      Recuperacao recuperacaoSalvo = recuperacaoService.salvar(recuperacao);
      return ResponseEntity.ok(recuperacaoSalvo);
    } catch (RegraNegocioException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  boolean diferencaDeTempoMaiorQue5Minutos(String hora1, String hora2) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime localTime1 = LocalTime.parse(hora1, formatter);
    LocalTime localTime2 = LocalTime.parse(hora2, formatter);

    long diferencaMinutos = Math.abs(localTime1.until(localTime2, java.time.temporal.ChronoUnit.MINUTES));

    return diferencaMinutos >= 5;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @PostMapping("/verificar/{codigo}")
  public ResponseEntity verificar(@PathVariable("codigo") String codigo, @RequestBody RecuperacaoDTO request) {
    return recuperacaoService.findByCodigo(codigo).map(entity -> {
      try {
        if (request.getEmail() != entity.getUsuario().getEmail()) {
          new ResponseEntity("Código inválido.", HttpStatus.BAD_REQUEST);
        }

        if (diferencaDeTempoMaiorQue5Minutos(entity.getHora(), request.getHora())) {
          recuperacaoService.excluir(entity);
          return ResponseEntity.badRequest().body("Código expirado.");
        }

        recuperacaoService.excluir(entity);
        return ResponseEntity.ok(entity);
      } catch (RegraNegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }).orElseGet(() -> new ResponseEntity("Código inválido.", HttpStatus.BAD_REQUEST));
  }

}
