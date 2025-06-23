package com.tienda.backend.controller;

import com.tienda.backend.dto.OrderRequest;
import com.tienda.backend.entity.Order;
import com.tienda.backend.entity.OrderItem;
import com.tienda.backend.entity.Product;
import com.tienda.backend.entity.User;
import com.tienda.backend.repository.UserRepository;
import com.tienda.backend.service.OrderService;
import com.tienda.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para clientes que quieren hacer o consultar pedidos
 */
@RestController
@RequestMapping("/api/cliente/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Order> crearPedido(@RequestBody OrderRequest request){
        List<OrderItem> items = new ArrayList<>();

        for (OrderRequest.ItemDTO itemDTO : request.getItems()){
            Product product = productRepository.findById(itemDTO.getProductId()).orElse(null);
            if (product == null) continue;

            OrderItem item =OrderItem.builder()
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .build();

            items.add(item);
        }

        // Obtener email desde el token (SecurityContextHolder)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar el usuario por su email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        // Crear el pedido con el userId real
        Order nuevoPedido = orderService.createOrder(user.getId(), items);
        return ResponseEntity.ok(nuevoPedido);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> verPedidos(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}
