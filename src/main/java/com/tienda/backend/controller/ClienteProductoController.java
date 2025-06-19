package com.tienda.backend.controller;

import com.tienda.backend.entity.Product;
import com.tienda.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para mostrar productos al cliente (acceso público)
 */

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ClienteProductoController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        Product producto = productService.getById(id);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }
}
