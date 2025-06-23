package com.tienda.backend.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase temporal que genera un hash de una contraseña al iniciar el proyecto.
 * Solo para uso en desarrollo (no se usa en producción).
 */
@Component
@RequiredArgsConstructor
public class HashGenerator {

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void generarHash() {
        String rawPassword = "123456";
        String hashed = passwordEncoder.encode(rawPassword);
        System.out.println("🔑 Hash generado para '123456': " + hashed);
    }
}
