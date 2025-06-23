package com.tienda.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO{
        private Long productId;
        private int quantity;
    }
}
