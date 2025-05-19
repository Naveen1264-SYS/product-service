//package com.ecom.microservices.product_service.dto;
//
//import java.math.BigDecimal;
//
//public record ProductRequest(String id, String name, String description,BigDecimal price) {
//    // This record will automatically generate a constructor, getters, and toString method
//    // based on the fields defined in the record.
//}
package com.ecom.microservices.product_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductRequest(
        String id,

        @NotBlank(message = "Product name cannot be blank")
        String name,

        String description,

        @NotNull(message = "Product price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price,

        String sku,
        String category,
        String brand,
        BigDecimal discountedPrice,
        Integer stockQuantity,
        Double weight,
        String color,
        List<String> imageUrls,
        List<String> tags,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isActive,
        Map<String, String> customAttributes
) {}

