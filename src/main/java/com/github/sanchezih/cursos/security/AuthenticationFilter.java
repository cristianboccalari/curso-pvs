package com.github.sanchezih.cursos.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends GenericFilterBean {

	/**
	 * En este metodo, se evalua el header API Key y establecemos el objeto
	 * Authentication resultante en la instancia actual de SecurityContext.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {

			// Delegamos la evaluacion de la API Key y la construccion del objeto
			// Authentication a la clase AuthenticationService
			Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);

			// Establece la informacion de autenticacion del usuario en el contexto de
			// seguridad de la aplicacion
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception exp) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			PrintWriter writer = httpResponse.getWriter();
			writer.print(exp.getMessage());
			writer.flush();
			writer.close();
		}

		filterChain.doFilter(request, response);
	}
}