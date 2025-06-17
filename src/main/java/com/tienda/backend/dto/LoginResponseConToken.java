package com.tienda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Respuesta del login con token JWT incluido.
 */

@Data
@AllArgsConstructor
public class LoginResponseConToken {
    private String name;
    private String email;
    private String role;
    private String token;
}
