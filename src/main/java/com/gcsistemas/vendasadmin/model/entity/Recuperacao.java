package com.gcsistemas.vendasadmin.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recuperacao", schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recuperacao {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "codigo")
  private String codigo;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @Column(name = "hora")
  private String hora;

}
