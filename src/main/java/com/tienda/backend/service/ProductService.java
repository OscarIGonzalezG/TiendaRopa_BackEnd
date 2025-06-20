package com.tienda.backend.service;

import com.tienda.backend.entity.Product;
import com.tienda.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll(){

        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }
}
