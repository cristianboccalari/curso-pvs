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

import com.github.sanchezih.cursos.dto.request.CursoRequestDTO;
import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Cursos", description = "Controlador para gestionar cursos")
public class CursoController {

	private final CursoService cursoService;

	/*----------------------------------------------------------------------------*/

	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	/*----------------------------------------------------------------------------*/

	@Operation(summary = "Crear un curso")
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CursoRequestDTO cursoRequestDTO) {
		Curso cursoCreado = cursoService.create(cursoRequestDTO);
		return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
	}

	@Operation(summary = "Obtener un curso")
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable(name = "id") Long cursoId) {
		Curso curso = cursoService.getOne(cursoId);
		return new ResponseEntity<>(curso, HttpStatus.OK);
	}

	@Operation(summary = "Obtener todos los cursos existentes al momento")
	@GetMapping
	public ResponseEntity<?> getAll(Pageable pageable) {
		Page<Curso> res = cursoService.getAll(pageable);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@Operation(summary = "Actualizar un curso")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CursoRequestDTO cursoRequestDTO,
			@PathVariable(name = "id") Long idCurso) {
		Curso res = cursoService.update(cursoRequestDTO, idCurso);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@Operation(summary = "Eliminar un curso")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		cursoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
