package com.github.sanchezih.cursos.repository;

import com.github.sanchezih.cursos.entity.Curso;
import com.github.sanchezih.cursos.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
