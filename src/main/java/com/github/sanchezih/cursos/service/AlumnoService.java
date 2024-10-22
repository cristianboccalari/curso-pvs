package com.github.sanchezih.cursos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.github.sanchezih.cursos.dto.request.AlumnoRequestDTO;
import com.github.sanchezih.cursos.entity.Alumno;

public interface AlumnoService {
	public Alumno create(Long cursoId, AlumnoRequestDTO alumnoRequestDTO);

	public Page<Alumno> getAllAlumnosByCursoId(Long cursoId, Pageable pageable);

	public Alumno getOne(Long cursoId, Long alumnoId);

	public AlumnoRequestDTO updateAlumno(Long cursoId, Long alumnoId, AlumnoRequestDTO alumnoRequestDTO);

	public void delete(Long cursoId, Long alumnoId);
}
