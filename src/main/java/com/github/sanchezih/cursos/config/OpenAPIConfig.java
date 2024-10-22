package com.github.sanchezih.cursos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("API de Cursos")
                    .version("1.0.0")
                    .description("Esta es la documentacion de la API de ejemplo.")
                    .contact(new Contact()
                        .name("Soporte")
                        .email("soporte@example.com")
                        .url("https://example.com")));
    }
}