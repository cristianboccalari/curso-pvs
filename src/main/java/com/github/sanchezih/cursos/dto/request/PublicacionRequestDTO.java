package com.github.sanchezih.cursos.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PublicacionRequestDTO {

	@NotEmpty
	@Size(min = 3, message = "El contenido debe tener al menos 3 caracteres")
	private String contenido;

}
