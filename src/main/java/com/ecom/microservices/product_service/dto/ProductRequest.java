package com.ecom.microservices.product_service.dto;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String description,BigDecimal price) {
    // This record will automatically generate a constructor, getters, and toString method
    // based on the fields defined in the record.
}
