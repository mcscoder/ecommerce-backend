package com.ecommerce.ecommercebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDTO {
    private Integer productId;
    private Integer quantity;
}
