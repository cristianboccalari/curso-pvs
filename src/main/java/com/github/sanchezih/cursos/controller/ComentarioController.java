package com.github.sanchezih.cursos.controller;

import com.github.sanchezih.cursos.dto.request.AlumnoRequestDTO;
import com.github.sanchezih.cursos.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.cursos.entity.Alumno;
import com.github.sanchezih.cursos.entity.Comentario;
import com.github.sanchezih.cursos.service.ComentarioService;
import com.github.sanchezih.cursos.service.PublicacionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Operation(summary = "Crear un comentario")
    @PostMapping
    public ResponseEntity<?> create(@PathVariable(name = "publicacionId") Long id,
                                    @Valid @RequestBody ComentarioRequestDTO comentarioRequestDTO) {

        Comentario comentario = comentarioService.create(id, comentarioRequestDTO);
        return new ResponseEntity<>(comentario, HttpStatus.CREATED);
    }


    @Operation(summary = "Obtener todos los comentarios pertenecientes a una publicación")
    @GetMapping
    public ResponseEntity<?> getAll(@PathVariable(value = "publicacionId") Long publicacionId, Pageable pageable) {
        Page<Comentario> comentarios = comentarioService.getAllComentariosByPublicacionId(publicacionId, pageable);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }



}
