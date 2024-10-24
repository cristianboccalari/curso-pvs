package com.github.sanchezih.cursos.service;

import com.github.sanchezih.cursos.dto.request.CursoRequestDTO;
import com.github.sanchezih.cursos.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.entity.Publicacion;
import com.github.sanchezih.cursos.exceptions.custom.ResourceNotFoundException;
import com.github.sanchezih.cursos.repository.CursoRepository;
import com.github.sanchezih.cursos.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService {

    private final ModelMapper modelMapper;
    @Autowired
    private PublicacionRepository publicacionRepository;

    public PublicacionService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Publicacion create(PublicacionRequestDTO publicacionRequestDTO) {
        Publicacion publicacionCrear = modelMapper.map(publicacionRequestDTO, Publicacion.class);
        Publicacion newPublicacion = publicacionRepository.save(publicacionCrear);
        return newPublicacion;
    }

    public Publicacion getOne(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return publicacion;
    }

    public Page<Publicacion> getAll(Pageable pageable) {
        return publicacionRepository.findAll(pageable);
    }



}
