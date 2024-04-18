package com.gcsistemas.novosigeve.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcsistemas.novosigeve.dto.EmpresaDTO;
import com.gcsistemas.novosigeve.exception.RegraNegocioException;
import com.gcsistemas.novosigeve.model.entity.Empresa;
import com.gcsistemas.novosigeve.model.entity.Usuario;
import com.gcsistemas.novosigeve.service.EmpresaService;
import com.gcsistemas.novosigeve.service.UsuarioService;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

  @Autowired
  private EmpresaService empresaService;

  @Autowired
  private UsuarioService usuarioService;

  @SuppressWarnings("rawtypes")
  @GetMapping
  public ResponseEntity pesquisar(
      @RequestParam(value = "nome", required = false) String nome) {

    Empresa empresaFiltro = new Empresa();
    empresaFiltro.setNome(nome);

    List<Empresa> empresas = empresaService.buscar(empresaFiltro);

    empresas.sort(Comparator.comparing(Empresa::getNome));

    return ResponseEntity.ok(empresas);
  }

  @SuppressWarnings("rawtypes")
  @PostMapping("/salvar")
  public ResponseEntity salvar(@RequestBody EmpresaDTO request) {
    Empresa entidade = converter(request);

    try {
      entidade = empresaService.salvar(entidade);
      return ResponseEntity.ok(entidade);
    } catch (RegraNegocioException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @PutMapping("{id}")
  public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody EmpresaDTO request) {
    return empresaService.buscaRegistro(id).map(entity -> {
      try {
        Empresa entidade = converter(request);
        entidade.setId(entity.getId());
        empresaService.salvar(entidade);
        return ResponseEntity.ok(entidade);
      } catch (RegraNegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }).orElseGet(() -> new ResponseEntity("Empresa não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @DeleteMapping("{id}")
  public ResponseEntity excluir(@PathVariable("id") Long id) {
    return empresaService.buscaRegistro(id).map(entity -> {
      try {
        empresaService.excluir(entity);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (RegraNegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }).orElseGet(() -> new ResponseEntity("Empresa não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @GetMapping("{id}")
  public ResponseEntity obterPorId(@PathVariable("id") Long id) {

    return empresaService.buscaRegistro(id)
        .map(empresa -> new ResponseEntity(empresa, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }

  private Empresa converter(EmpresaDTO dto) {

    Usuario usuario = usuarioService.findById(dto.getUsuarioId())
        .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado para o id informado"));

    return Empresa.builder()
        .id(dto.getId())
        .nome(dto.getNome())
        .cpfCnpj(dto.getCpfCnpj())
        .email(dto.getEmail())
        .telefone(dto.getTelefone())
        .inscricao(dto.getInscricao())
        .cep(dto.getCep())
        .endereco(dto.getEndereco())
        .uf(dto.getUf())
        .cidade(dto.getCidade())
        .bairro(dto.getBairro())
        .observacao(dto.getObservacao())
        .dataCadastro(new Date())
        .proximoNumeroNota(dto.getProximoNumeroNota())
        .usuario(usuario)
        .build();

  }

  private EmpresaDTO converter(Empresa empresa) {

    return EmpresaDTO.builder()
        .id(empresa.getId())
        .nome(empresa.getNome())
        .usuarioId(empresa.getUsuario().getId())
        .build();
  }

}
