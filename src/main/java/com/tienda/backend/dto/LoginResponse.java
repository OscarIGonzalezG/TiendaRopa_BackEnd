package com.tienda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO que representa la respuesta después de un login exitoso.
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
}
