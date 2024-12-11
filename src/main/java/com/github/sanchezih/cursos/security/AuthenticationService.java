package com.github.sanchezih.cursos.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

	private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
	private static final String AUTH_TOKEN = "Java2024";

	/*----------------------------------------------------------------------------*/

	/**
	 * Aqui verificamos si la solicitud contiene el header API Key con un secret o
	 * no.
	 * 
	 * Si el encabezado es nulo o no es igual al secreto, lanzamos una
	 * BadCredentialsException.
	 * 
	 * Si la solicitud tiene el encabezado, realiza la autenticacion, agrega el
	 * secreto al contexto de seguridad y luego pasa la llamada al siguiente filtro
	 * de seguridad.
	 * 
	 * @param request
	 * @return
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {

		String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

		if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
			throw new BadCredentialsException("API Key invalida");
		}

		ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);

		return apiKeyAuthentication;
	}
}