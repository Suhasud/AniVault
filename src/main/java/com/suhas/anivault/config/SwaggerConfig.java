package com.suhas.anivault.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI aniVaultOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                .info(new Info()
                        .title("AniVault REST API")
                        .description("REST API for managing your anime watchlist.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Suhas U D")
                                .email("example@email.com")))

                .servers(java.util.List.of(
                        new Server()
                                .url("https://anivault-production-74a7.up.railway.app")
                                .description("Production Server")
                ))

                .externalDocs(new ExternalDocumentation()
                        .description("AniVault Documentation"))

                // JWT Security Configuration
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))

                .schemaRequirement(
                        securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));

    }
}