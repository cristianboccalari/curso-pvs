package com.github.sanchezih.cursos.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CursoRequestDTO {

	@NotEmpty
	@Size(min = 3, message = "La materia del curso debe tener al menos 3 caracteres")
	private String materia;

}
