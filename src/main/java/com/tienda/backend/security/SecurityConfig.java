// solo para deshabilitar la seguridad para las pruebas en POSTMAN
package com.tienda.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tienda.backend.security.JwtFilter;
/**
 * Clase de configuración para beans relacionados con seguridad.
 * Configuración personalizada de seguridad para permitir endpoints públicos.
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->csrf.disable())  // Desactiva protección CSRF (solo para desarrollo)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login permitido
                        .requestMatchers("/api/users/**").hasAuthority("ADMIN") // Solo ADMIN
                        .requestMatchers("/api/vendedor/**").hasAuthority("VENDEDOR") // Solo VENDEDOR
                        .requestMatchers("/api/productos/**").permitAll() // ← acceso libre.
                        .anyRequest().authenticated()// otras rutas requieren login
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        // return http.build();
    }
    // Been que permite encriptar contraseñas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
