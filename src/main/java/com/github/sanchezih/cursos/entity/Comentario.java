package com.github.sanchezih.cursos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuerpo", nullable = false)
    private String cuerpo;
    private String email;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(//
            name = "publicacion_id", //
            nullable = false //
    )
    private Publicacion publicacion;


}
