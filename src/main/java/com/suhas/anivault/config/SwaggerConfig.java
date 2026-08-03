package com.suhas.anivault.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI aniVaultOpenAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("AniVault REST API")
                        .description("REST API for managing your anime watchlist.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Suhas U D")
                                .email("example@email.com")))

                .externalDocs(new ExternalDocumentation()
                        .description("AniVault Documentation"));
    }
}