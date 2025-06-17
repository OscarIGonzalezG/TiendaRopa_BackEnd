// solo para deshabilitar la seguridad para las pruebas en POSTMAN
package com.tienda.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuración para beans relacionados con seguridad.
 * Configuración personalizada de seguridad para permitir endpoints públicos.
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->csrf.disable())  // Desactiva protección CSRF (solo para desarrollo)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login permitido
                        .requestMatchers("/api/users/**").permitAll() // Para pruebas
                        .anyRequest().authenticated()// otras rutas requieren login
                );
        return http.build();
    }
    // Been que permite encriptar contraseñas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
