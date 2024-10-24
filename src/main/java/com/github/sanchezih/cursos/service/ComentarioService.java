package com.github.sanchezih.cursos.service;

import com.github.sanchezih.cursos.dto.request.AlumnoRequestDTO;
import com.github.sanchezih.cursos.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.cursos.entity.Alumno;
import com.github.sanchezih.cursos.entity.Comentario;
import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.entity.Publicacion;
import com.github.sanchezih.cursos.exceptions.custom.ResourceNotFoundException;
import com.github.sanchezih.cursos.repository.ComentarioRepository;
import com.github.sanchezih.cursos.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ModelMapper modelMapper;
    @Autowired private ComentarioRepository comentarioRepository;
    @Autowired private PublicacionRepository publicacionRepository;
    @Autowired private PublicacionService publicacionService;

    public ComentarioService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Comentario create(Long publicacionId, ComentarioRequestDTO comentarioRequestDTO) {
        Publicacion publicacion = publicacionService.getOne(publicacionId);
        Comentario comentario = modelMapper.map(comentarioRequestDTO, Comentario.class);
        comentario.setPublicacion(publicacion);

        return comentarioRepository.save(comentario);
    }

    /**
     *
     */
    public Page<Comentario> getAllComentariosByPublicacionId(Long publicacionId, Pageable pageable) {
        publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        return comentarioRepository.findByPublicacionId(publicacionId, pageable);
    }

}
