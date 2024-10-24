package com.github.sanchezih.cursos.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ComentarioRequestDTO {

    @NotEmpty(message = "El cuerpo no debe ser vacio o nulo")
    private String cuerpo;

}
