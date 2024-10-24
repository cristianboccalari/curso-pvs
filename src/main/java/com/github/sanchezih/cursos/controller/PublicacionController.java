package com.github.sanchezih.cursos.controller;

import com.github.sanchezih.cursos.dto.request.CursoRequestDTO;
import com.github.sanchezih.cursos.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.entity.Publicacion;
import com.github.sanchezih.cursos.service.CursoService;
import com.github.sanchezih.cursos.service.PublicacionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventPublicationInterceptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;


    @Operation(summary = "Crear una publicaciòn")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO) {
        Publicacion publicacion = publicacionService.create(publicacionRequestDTO);
        return new ResponseEntity<>(publicacion, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una publicación")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        Publicacion publicacion = publicacionService.getOne(id);
        return new ResponseEntity<>(publicacion, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todos las publicaciones existentes")
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Publicacion> res = publicacionService.getAll(pageable);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

}
