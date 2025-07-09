package com.projexio.taskora_back.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import java.nio.charset.StandardCharsets
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(HttpMethod.DELETE, "/api/tickets/**").authenticated()
                    .anyRequest().permitAll()
            }
            .oauth2ResourceServer { oauth2 -> oauth2.jwt { jwt -> jwt.decoder(jwtDecoder()) } }
        return http.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        // Exemple simple avec clé secrète (HMAC)
        val secretKey = "change_this_to_a_real_secret_key_1234567890"
        val keyBytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        val secretKeySpec = SecretKeySpec(keyBytes, "HmacSHA256")

        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build()
    }
}