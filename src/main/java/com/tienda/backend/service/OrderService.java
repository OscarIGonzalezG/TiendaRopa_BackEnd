package com.tienda.backend.service;

import com.tienda.backend.entity.*;
import com.tienda.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order createOrder(Long userId, List<OrderItem> items){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("❌ Usuario no encontrado con ID: " + userId));

        Order order = Order.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .isPaid(false)
                .build();

        BigDecimal total = BigDecimal.ZERO;
        // Asociar cada item con el pedido
        for (OrderItem item : items){
            Product product = productRepository.findById(item.getProduct().getId()).orElseThrow();

            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(subtotal);

            item.setProduct(product);
            item.setOrder(order);
            item.setPrice(product.getPrice());
        }

        order.setItems(items);
        order.setTotal(total);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId){
        return orderRepository.findByUserId(userId);
    }
}
