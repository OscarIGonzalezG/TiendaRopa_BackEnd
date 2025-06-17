package com.tienda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // Indica que esta clase representa una tabla en la DB
@Table(name = "users") // Especifica el nombre real de la tabla
@Getter @Setter // Genera automáticamente los getters y setters (Lombok)
@NoArgsConstructor // Constructor Vacío
@AllArgsConstructor // Constructor con todos los campos
@Builder // Permite crear objetos con patrón Builder
public class User {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long id;

    @Column(nullable = false, length = 100) // No puede ser nulo
    private String name;

    @Column(nullable = false, unique = true, length = 150) // Único y obligatorio
    private String email;

    @Column(nullable = false, length = 255) // Encriptada más adelante
    private String password;

    @Column(nullable = false, length = 20)
    private String role; // Puede ser: ADMIN, VENDEDOR, CLIENTE

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Builder.Default
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Almacena fecha creación

}
