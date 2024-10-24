package com.github.sanchezih.cursos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "publicacion")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contenido", nullable = false)
    private String contenido;
    private String titulo;

    @JsonBackReference
    @OneToMany( //
            mappedBy = "publicacion", //
            cascade = CascadeType.ALL, //
            orphanRemoval = true //
    )
    private Set<Comentario> comentarios = new HashSet<>();



}
