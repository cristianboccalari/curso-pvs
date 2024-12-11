package com.github.sanchezih.cursos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http

				// Deshabilitar la proteccion contra ataques CSRF (Cross-Site Request Forgery).
				// (*)
				.csrf(AbstractHttpConfigurer::disable)

				// Todas las rutas de la aplicacion requieren autenticacion.
				.authorizeHttpRequests(authorizeRequest -> authorizeRequest.requestMatchers("/**").authenticated())

				// Habilitar la autenticacion basica (HTTP Basic Authentication) utilizando los
				// valores predeterminados que ofrece Spring Security.
				.httpBasic(Customizer.withDefaults())

				// Se configura la aplicacion para que sea sin estado, lo que significa que no
				// se utilizaran sesiones para gestionar la autenticacion o el estado del
				// usuario.
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Agrega un filtro personalizado en la cadena de filtros de seguridad. En este
				// caso, el filtro AuthenticationFilter se esta añadiendo antes de
				// UsernamePasswordAuthenticationFilter.
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// Paso final en la configuracion de seguridad de Spring. Se crea y devuelve un
		// SecurityFilterChain.
		return http.build();
	}

}

/*
 * (*) El ataque CSRF ocurre cuando un usuario autenticado realiza una solicitud
 * maliciosa sin su conocimiento. Para prevenir estos ataques, Spring Security
 * tiene habilitada la protección CSRF por defecto en las aplicaciones web que
 * usan sesiones (como las basadas en formularios). Sin embargo, en algunas
 * circunstancias (por ejemplo, cuando estas creando una API REST), podrias
 * deshabilitar esta proteccion ya que las API REST suelen no necesitarla debido
 * a que no usan cookies para el manejo de la autenticacion.
 */