package com.projexio.taskora_back.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Taskora API")
                    .description("API pour la gestion de tickets (Taskora)")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Projexio Team")
                            .email("contact@projexio.com")
                            .url("https://projexio.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                    )
            )
    }

    @Bean
    fun protectedApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("protected")
            .pathsToMatch("/api/**")
            .pathsToExclude("/api/public/**")
            .addOpenApiCustomizer { openApi ->
                openApi.components.addSecuritySchemes(
                    "bearerAuth", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
                openApi.addSecurityItem(SecurityRequirement().addList("bearerAuth"))
            }
            .build()
    }

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/api/public/**")
            .build()
    }
}