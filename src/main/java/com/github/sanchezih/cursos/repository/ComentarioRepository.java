package com.github.sanchezih.cursos.repository;

import com.github.sanchezih.cursos.entity.Alumno;
import com.github.sanchezih.cursos.entity.Comentario;
import com.github.sanchezih.cursos.entity.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public Page<Comentario> findByPublicacionId(Long publicacionId, Pageable pageable);

}
