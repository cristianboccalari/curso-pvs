package com.github.sanchezih.cursos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.sanchezih.cursos.dto.request.CursoRequestDTO;
import com.github.sanchezih.cursos.entity.Curso;

public interface CursoService {
	public Curso create(CursoRequestDTO cursoRequestDTO);

	public Curso getOne(Long id);

	public Page<Curso> getAll(Pageable pageable);

	public Curso update(CursoRequestDTO cursoRequestDTO, Long id);

	public void delete(Long id);
}
