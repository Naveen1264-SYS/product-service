package com.ecom.microservices.product_service.repository;

import com.ecom.microservices.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository  extends MongoRepository<Product, String> {
    // This interface will automatically provide CRUD operations for the Product entity
    // based on the methods defined in JpaRepository.
    // You can also define custom query methods here if needed.
}
