package com.github.sanchezih.cursos.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Long id;

	@Column(name = "materia", nullable = false)
	private String materia;

	@JsonBackReference
	@OneToMany( //
			mappedBy = "curso", //
			cascade = CascadeType.ALL, //
			orphanRemoval = true //
	)
	private Set<Alumno> alumnos = new HashSet<>();

	public Curso() {
	}

	public Curso(Long id, String materia, Set<Alumno> alumnos) {
		super();
		this.id = id;
		this.materia = materia;
		this.alumnos = alumnos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

}
