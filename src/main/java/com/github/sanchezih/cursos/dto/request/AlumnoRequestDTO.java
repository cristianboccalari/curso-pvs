package com.github.sanchezih.cursos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AlumnoRequestDTO {

	@NotEmpty(message = "El nombre no debe ser vacio o nulo")
	private String nombre;

	@NotEmpty(message = "El email no debe ser vacio o nulo")
	@Email
	private String email;

}
