package com.tienda.backend.config;

import com.tienda.backend.entity.User;
import com.tienda.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component // Spring la detecta y ejecuta automáticamente
@RequiredArgsConstructor // Genera constructor con dependencias inyectadas (Lombok)
public class DataLoader  implements CommandLineRunner{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{

        // Si ya existen usuarios, no cargamos nada
        if (userRepository.count() > 0) return;

        // Crear y guardar usuarios de prueba
        User admin1 = User.builder()
                .name("Admin Principal")
                .email("admin1@tienda.com")
                .password(passwordEncoder.encode("123456")) // luego encriptaremos esto
                .role("ADMIN")
                .build();

        User admin2 = User.builder()
                .name("Admin Secundario")
                .email("admin2@tienda.com")
                .password(passwordEncoder.encode("123456"))
                .role("ADMIN")
                .build();

        User vendedor1 = User.builder()
                .name("Vendedor Uno")
                .email("vendedor1@tienda.com")
                .password(passwordEncoder.encode("123456"))
                .role("VENDEDOR")
                .build();

        User vendedor2 = User.builder()
                .name("Vendedora Dos")
                .email("vendedora2@tienda.com")
                .password(passwordEncoder.encode("123456"))
                .role("VENDEDOR")
                .build();

        // Guardar todos en la DB
        userRepository.save(admin1);
        userRepository.save(admin2);
        userRepository.save(vendedor1);
        userRepository.save(vendedor2);

        System.out.println("🟢 Usuarios de prueba insertados exitosamente.");
    }
}
