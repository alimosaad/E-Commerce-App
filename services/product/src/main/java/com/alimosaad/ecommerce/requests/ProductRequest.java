package com.alimosaad.ecommerce.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

public record ProductRequest(
         Integer id,
         @NotNull(message = "Product name is required")
         String name,
         @NotNull(message = "Product description is required")
         String description,
         @Positive(message = "Available Quantity should be positive")
         double availableQuantity,
         @Positive(message = "Price should be positive")
         BigDecimal price,
         @NotNull(message = "Product categoryId is required")
         Integer categoryId
) {
}
