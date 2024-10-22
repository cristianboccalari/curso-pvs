package com.github.sanchezih.cursos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.sanchezih.cursos.dto.request.AlumnoRequestDTO;
import com.github.sanchezih.cursos.entity.Alumno;
import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.exceptions.custom.BadRequestException;
import com.github.sanchezih.cursos.exceptions.custom.ResourceNotFoundException;
import com.github.sanchezih.cursos.repository.AlumnoRepository;
import com.github.sanchezih.cursos.repository.CursoRepository;
import com.github.sanchezih.cursos.service.AlumnoService;
import com.github.sanchezih.cursos.service.CursoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	private static final String ALUMNO_NO_PERTENECE_AL_CURSO = "El alumno no pertenece al curso";

	private final ModelMapper modelMapper;
	private final AlumnoRepository alumnoRepository;
	private final CursoRepository cursoRepository;
	private final CursoService cursoService;

	/*----------------------------------------------------------------------------*/

	/**
	 * Inyeccion de dependencias por constructor
	 * 
	 * @param modelMapper
	 * @param alumnoRepository
	 * @param cursoRepository
	 * @param cursoService
	 */
	public AlumnoServiceImpl(ModelMapper modelMapper, AlumnoRepository alumnoRepository,
			CursoRepository cursoRepository, CursoService cursoService) {
		this.modelMapper = modelMapper;
		this.alumnoRepository = alumnoRepository;
		this.cursoRepository = cursoRepository;
		this.cursoService = cursoService;
	}

	/*----------------------------------------------------------------------------*/

	@Override
	public Alumno create(Long cursoId, AlumnoRequestDTO alumnoRequestDTO) {
		Curso curso = cursoService.getOne(cursoId);
		Alumno alumno = mapAlumnoRequestDTOToAlumno(alumnoRequestDTO);
		alumno.setCurso(curso);
		return alumnoRepository.save(alumno);
	}

	/**
	 * 
	 */
	@Override
	public Page<Alumno> getAllAlumnosByCursoId(Long cursoId, Pageable pageable) {
		cursoRepository.findById(cursoId).orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));
		return alumnoRepository.findByCursoId(cursoId, pageable);
	}

	@Override
	public Alumno getOne(Long cursoId, Long alumnoId) {

		// Busco si el curso existe
		Curso publicacion = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));

		// Busco si el alumno existe
		Alumno comentario = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", alumnoId));

		// Busco si el alumno pertenece al curso
		if (!comentario.getCurso().getId().equals(publicacion.getId())) {
			throw new BadRequestException(ALUMNO_NO_PERTENECE_AL_CURSO);
		}

		return comentario;
	}

	@Override
	public AlumnoRequestDTO updateAlumno(Long cursoId, Long alumnoId, AlumnoRequestDTO alumnoRequestDTO) {

		// Busco si el curso existe
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));

		// Busco si el alumno existe
		Alumno alumno = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", alumnoId));

		// Busco si el alumno pertenece al curso
		if (!alumno.getCurso().getId().equals(curso.getId())) {
			throw new BadRequestException(ALUMNO_NO_PERTENECE_AL_CURSO);
		}

		alumno.setNombre(alumnoRequestDTO.getNombre());
		alumno.setEmail(alumnoRequestDTO.getEmail());

		Alumno alumnoActualizado = alumnoRepository.save(alumno);
		return mapAlumnoToAlumnoDTO(alumnoActualizado);
	}

	@Override
	public void delete(Long cursoId, Long alumnoId) {

		// Busco si el curso existe
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));

		// Busco si el alumnoo existe
		Alumno alumno = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", alumnoId));

		// Busco si el alumno pertenece al curso
		if (!alumno.getCurso().getId().equals(curso.getId())) {
			throw new BadRequestException(ALUMNO_NO_PERTENECE_AL_CURSO);
		}

		alumnoRepository.delete(alumno);
	}

	/**
	 * 
	 * @param alumnoRequestDTO
	 * @return
	 */
	private Alumno mapAlumnoRequestDTOToAlumno(AlumnoRequestDTO alumnoRequestDTO) {
		return modelMapper.map(alumnoRequestDTO, Alumno.class);
	}

	/**
	 * 
	 * @param alumno
	 * @return
	 */
	private AlumnoRequestDTO mapAlumnoToAlumnoDTO(Alumno alumno) {
		return modelMapper.map(alumno, AlumnoRequestDTO.class);
	}

}
