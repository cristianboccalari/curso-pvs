package com.github.sanchezih.cursos.dto.response;

import java.util.List;

import com.github.sanchezih.cursos.dto.request.CursoRequestDTO;

import lombok.Data;

@Data
public class CursoResponseDTO {
	private List<CursoRequestDTO> contenido;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
