package com.github.sanchezih.cursos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.sanchezih.cursos.dto.request.CursoRequestDTO;
import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.exceptions.custom.ResourceNotFoundException;
import com.github.sanchezih.cursos.repository.CursoRepository;
import com.github.sanchezih.cursos.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

	private final ModelMapper modelMapper;
	private final CursoRepository cursoRepository;

	/*----------------------------------------------------------------------------*/

	public CursoServiceImpl(ModelMapper modelMapper, CursoRepository cursoRepository) {
		this.modelMapper = modelMapper;
		this.cursoRepository = cursoRepository;
	}

	/*----------------------------------------------------------------------------*/

	@Override
	public Curso create(CursoRequestDTO cursoRequestDTO) {
		Curso cursoACrear = mapCursoRequestDTOToCurso(cursoRequestDTO);
		Curso cursoCreado = cursoRepository.save(cursoACrear);
		return cursoCreado;
	}

	@Override
	public Curso getOne(Long id) {
		Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));
		return curso;
	}

	@Override
	public Page<Curso> getAll(Pageable pageable) {
		return cursoRepository.findAll(pageable);
	}

	@Override
	public Curso update(CursoRequestDTO cursoRequestDTO, Long id) {
		Curso cursoAActualizar = cursoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));

		cursoAActualizar.setMateria(cursoRequestDTO.getMateria());

		Curso cursoActualizado = cursoRepository.save(cursoAActualizar);
		return cursoActualizado;
	}

	@Override
	public void delete(Long id) {
		Curso cursoAEliminar = cursoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));
		cursoRepository.delete(cursoAEliminar);
	}

	private Curso mapCursoRequestDTOToCurso(CursoRequestDTO cursoRequestDTO) {
		Curso curso = modelMapper.map(cursoRequestDTO, Curso.class);
		return curso;
	}

}
