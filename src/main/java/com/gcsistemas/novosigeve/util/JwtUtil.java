package com.gcsistemas.novosigeve.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gcsistemas.novosigeve.model.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private int expiration;

  public String generateToken(Usuario usuario) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", usuario.getId());
    claims.put("nome", usuario.getNome());
    claims.put("email", usuario.getEmail());
    claims.put("senha", usuario.getSenha());
    claims.put("dataAtualizacao", usuario.getDataAtualizacao());
    claims.put("dataCadastro", usuario.getDataCadastro());

    return createToken(claims, secret);
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public Boolean validateToken(String token) {
    return (!isTokenExpired(token));
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public Usuario extractUsuario(String token) {
    final Claims claims = extractAllClaims(token);
    Usuario usuario = new Usuario();
    usuario.setId(claims.get("id", Long.class));
    usuario.setNome(claims.get("nome", String.class));
    usuario.setEmail(claims.get("email", String.class));
    usuario.setSenha(claims.get("senha", String.class));
    usuario.setDataAtualizacao(claims.get("dataAtualizacao", Date.class));
    usuario.setDataCadastro(claims.get("dataCadastro", Date.class));

    return usuario;
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}
