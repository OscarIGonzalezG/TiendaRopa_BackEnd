package com.tienda.backend.controller;

import com.tienda.backend.dto.LoginRequest;
import com.tienda.backend.dto.LoginResponse;
import com.tienda.backend.dto.LoginResponseConToken;
import com.tienda.backend.entity.User;
import com.tienda.backend.repository.UserRepository;

import com.tienda.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

/**
 * Controlador para la autenticación de usuarios (login)
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Endpoint POST /api/auth/login
     * Valida credenciales de usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        // Validar si el usuario existe
        if (userOptional.isEmpty()){
            return ResponseEntity.badRequest().body("Error: Usuario no registrado");
        }

        User user = userOptional.get();

        // Validar la contraseña con BCrypt
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.badRequest().body("Error: Contraseña incorrecta");
        }

        // Sí pasa ambas validaciones, retornar los datos básicos (sin password), Validación menos estructurada y solo de pruebas.
        //return ResponseEntity.ok("Login exitoso: " + user.getName() + " (" + user.getRole() + ")");

        // Generar token JWT
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        // Devolver respuesta con token
        LoginResponseConToken response = new LoginResponseConToken(
                user.getName(),
                user.getEmail(),
                user.getRole(),
                token
        );

        return ResponseEntity.ok(response);
    }
}
