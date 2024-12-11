package com.github.sanchezih.cursos.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * La clase AbstractAuthenticationToken implementa la Interface Authentication,
 * representando el secreto/principal de una solicitud autenticada.
 */
public class ApiKeyAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private final String apiKey;

	/* cambio----------------------------------------------------------------------------*/

	public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.apiKey = apiKey;
		setAuthenticated(true);
	}

	/*----------------------------------------------------------------------------*/

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return apiKey;
	}

}