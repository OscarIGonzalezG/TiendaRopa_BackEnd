package com.tienda.backend.repository;

import com.tienda.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Marca como componente de acceso a datos
public interface UserRepository extends JpaRepository<User, Long>{

    // Buscar por email (para login. validaciones, etc.)
    Optional<User> findByEmail(String email);

    // Validar si ya existe un email
    boolean existsByEmail(String email);
}
