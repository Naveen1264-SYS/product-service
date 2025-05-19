//package com.ecom.microservices.product_service.dto;
//
//import java.math.BigDecimal;
//
//public record ProductResponse (String id, String name, String description, BigDecimal price){
//}
package com.ecom.microservices.product_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductResponse(
        String id,
        String name,
        String description,
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
