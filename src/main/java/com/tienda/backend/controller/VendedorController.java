package com.tienda.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard(){
        return ResponseEntity.ok("Bienvenido al panel de vendedor");
    }
}
