package com.github.sanchezih.cursos.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sanchezih.cursos.dto.request.AlumnoRequestDTO;
import com.github.sanchezih.cursos.entity.Alumno;
import com.github.sanchezih.cursos.service.AlumnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cursos/{cursoId}/alumnos")
@Tag(name = "Alumnos", description = "Controlador para gestionar alumnos")
public class AlumnoController {

	private final AlumnoService alumnoService;

	/*----------------------------------------------------------------------------*/

	public AlumnoController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	/*----------------------------------------------------------------------------*/

	@Operation(summary = "Crear un alumno")
	@PostMapping
	public ResponseEntity<?> create(@PathVariable(name = "cursoId") Long curId,
			@Valid @RequestBody AlumnoRequestDTO alumnoRequestDTO) {

		Alumno alumnoCreado = alumnoService.create(curId, alumnoRequestDTO);
		return new ResponseEntity<>(alumnoCreado, HttpStatus.CREATED);
	}

	@Operation(summary = "Obtener un alumno")
	@GetMapping("/{alumnoId}")
	public ResponseEntity<?> getOne(@PathVariable(name = "cursoId") Long curId,
			@PathVariable(value = "alumnoId") Long comenId) {

		Alumno comentario = alumnoService.getOne(curId, comenId);
		return new ResponseEntity<>(comentario, HttpStatus.OK);
	}

	@Operation(summary = "Obtener todos los alumnos pertenecientes a un curso")
	@GetMapping
	public ResponseEntity<?> getAll(@PathVariable(value = "cursoId") Long pubId, Pageable pageable) {
		Page<Alumno> comentarios = alumnoService.getAllAlumnosByCursoId(pubId, pageable);
		return new ResponseEntity<>(comentarios, HttpStatus.OK);
	}

	@Operation(summary = "Actualizar un alumno")
	@PutMapping("/{comentarioId}")
	public ResponseEntity<?> update(@PathVariable Long cursoId, @PathVariable Long comentarioId,
			@Valid @RequestBody AlumnoRequestDTO alumnoRequestDTO) {

		AlumnoRequestDTO comentarioActualizado = alumnoService.updateAlumno(cursoId, comentarioId,
				alumnoRequestDTO);
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un alumno")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long cursoId, @PathVariable(name = "id") Long alumnoId) {
		alumnoService.delete(cursoId, alumnoId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
