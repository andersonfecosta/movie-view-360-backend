package com.movieview360.movieview360;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Movie View 360 Backend",
		description = "Aplicação web estilo Netflix, backend desenvolvido em Spring Boot e Java",
		version = "1.0.0"))
@SecuritySchemes({
		@SecurityScheme(
				name = "bearerAuth",
				scheme = "bearer",
				bearerFormat = "JWT",
				type = SecuritySchemeType.HTTP,
				in = SecuritySchemeIn.HEADER
		)
})
public class MovieView360Application {

	public static void main(String[] args) {
		SpringApplication.run(MovieView360Application.class, args);
	}

}
