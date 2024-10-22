package com.github.sanchezih.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sanchezih.cursos.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
