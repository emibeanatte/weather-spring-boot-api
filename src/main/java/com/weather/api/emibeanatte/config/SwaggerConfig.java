package com.weather.api.emibeanatte.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    /**
     * Método personalizado para crear y configurar el objeto OpenAPI con
     * ajustes personalizados. Este método crea un objeto OpenAPI y establece
     * los requisitos de seguridad, esquemas de seguridad, título, descripción,
     * versión, información de contacto y licencia para la documentación de la
     * API.
     *
     * @return el objeto OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("API de datos meteorologicos.")
                        .description("Comunicación con API externa OpenWeatherMap.")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Emiliano Beanatte")
                                .email("emibeanatte98@gmail.com")
                                .url("https://github.com/emibeanatte"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    /**
     * Crea y devuelve un esquema de seguridad de autenticación Bearer para la
     * API. El esquema es de tipo HTTP y utiliza el formato Bearer. El nombre
     * del esquema es "bearerAuth".
     *
     * @return El esquema de seguridad de la clave API
     */
    private io.swagger.v3.oas.models.security.SecurityScheme createAPIKeyScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme().type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
