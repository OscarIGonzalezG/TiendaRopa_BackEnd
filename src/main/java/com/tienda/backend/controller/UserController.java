package com.tienda.backend.controller;

import com.tienda.backend.entity.User;
import com.tienda.backend.repository.UserRepository;
import com.tienda.backend.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

/**
 * Controlador REST para gestionar los usuarios.
 * Expone endpoints relacionados a la entidad User (por ahora solo listar).
 */

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/users") // Prefijo común para todas las rutas de este controlador
@RequiredArgsConstructor // Genera un constructor con los atributos finales
public class UserController {

    // Inyección automática del repositorio de usuarios
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Endpoint GET /api/users
     * Retornar la lista completa de usuarios registrados en la DB
     *
     * @return ResponseEntity con la lista de usuarios y código HTTP 200
     */

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        // Obtener todos los usuarios usando el repositorio
        List<User> users = userRepository.findAll();

        // Retornar la lista como respuesta con estado Ok (200)
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint GET /api/users/{id}
     * Retorna un usuario por su ID si existe, o un 404 si no se encuentra.
     *
     * @param id Identificador del usuario
     * @return ResponseEntity con el usuario o error 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .map(ResponseEntity::ok)// Si lo encuentra, devuelve 200 OK + usuario
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no, 404 Not Found
    }

    /**
     * Endpoint POST /api/users
     * Crear un nuevo usuario con los datos enviados.
     *
     * @param request DTO con los datos del nuevo usuario
     * @return Usuario creado
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request){
        // Verificar si ya existe un usuario con ese email
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body("Error: El email ya está registrado");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Password encriptada
                .role(request.getRole())
                .build();

        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }
}
