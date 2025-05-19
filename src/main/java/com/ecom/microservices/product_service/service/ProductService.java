//package com.ecom.microservices.product_service.service;
//
//import com.ecom.microservices.product_service.dto.ProductRequest;
//import com.ecom.microservices.product_service.dto.ProductResponse;
//import com.ecom.microservices.product_service.model.Product;
//import com.ecom.microservices.product_service.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ProductService {
//
//     private final ProductRepository productRepository;
//
//     public ProductResponse createProduct(ProductRequest productRequest) {
//         Product product= Product.builder()
//                          .name(productRequest.name())
//                         .description(productRequest.description())
//                          .price(productRequest.price())
//                          .build();
//          productRepository.save(product);
//          log.info("Product created successfully");
//          return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
//     }
//
//    public List<ProductResponse> getAllProducts() {
//    return productRepository.findAll()
//            .stream()
//            .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
//            .toList();
//     }
//}

//package com.ecom.microservices.product_service.service;
//
//import com.ecom.microservices.product_service.dto.ProductRequest;
//import com.ecom.microservices.product_service.dto.ProductResponse;
//import com.ecom.microservices.product_service.model.Product;
//import com.ecom.microservices.product_service.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    public ProductResponse createProduct(ProductRequest productRequest) {
//        Product product = Product.builder()
//                .name(productRequest.name())
//                .description(productRequest.description())
//                .price(productRequest.price())
//                .sku(productRequest.sku())
//                .category(productRequest.category())
//                .brand(productRequest.brand())
//                .discountedPrice(productRequest.discountedPrice())
//                .stockQuantity(productRequest.stockQuantity())
//                .weight(productRequest.weight())
//                .color(productRequest.color())
//                .imageUrls(productRequest.imageUrls())
//                .tags(productRequest.tags())
//                .slug(productRequest.slug())
//                .customAttributes(productRequest.customAttributes())
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .isActive(true)
//                .build();
//
//        Product savedProduct = productRepository.save(product);
//        log.info("Product created successfully with ID: {}", savedProduct.getId());
//
//        return mapToProductResponse(savedProduct);
//    }
//
//    public List<ProductResponse> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream()
//                .map(this::mapToProductResponse)
//                .toList();
//    }
//
//    public ProductResponse getProductById(String id) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> {
//                    log.error("Product not found with ID: {}", id);
//                    return new NoSuchElementException("Product not found with ID: " + id);
//                });
//        return mapToProductResponse(product);
//    }
//
//    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
//        Product existingProduct = productRepository.findById(id)
//                .orElseThrow(() -> {
//                    log.error("Product not found with ID: {}", id);
//                    return new NoSuchElementException("Product not found with ID: " + id);
//                });
//
//        Product updatedProduct = Product.builder()
//                .id(id)
//                .name(productRequest.name())
//                .description(productRequest.description())
//                .price(productRequest.price())
//                .sku(productRequest.sku())
//                .category(productRequest.category())
//                .brand(productRequest.brand())
//                .discountedPrice(productRequest.discountedPrice())
//                .stockQuantity(productRequest.stockQuantity())
//                .weight(productRequest.weight())
//                .color(productRequest.color())
//                .imageUrls(productRequest.imageUrls())
//                .tags(productRequest.tags())
//                .slug(productRequest.slug())
//                .customAttributes(productRequest.customAttributes())
//                .createdAt(existingProduct.getCreatedAt())
//                .updatedAt(LocalDateTime.now())
//                .isActive(productRequest.isActive() != null ? productRequest.isActive() : existingProduct.getIsActive())
//                .build();
//
//        Product savedProduct = productRepository.save(updatedProduct);
//        log.info("Product updated successfully with ID: {}", id);
//
//        return mapToProductResponse(savedProduct);
//    }
//
//    public void deleteProduct(String id) {
//        if (!productRepository.existsById(id)) {
//            log.error("Product not found with ID: {}", id);
//            throw new NoSuchElementException("Product not found with ID: " + id);
//        }
//        productRepository.deleteById(id);
//        log.info("Product deleted successfully with ID: {}", id);
//    }
//
//    private ProductResponse mapToProductResponse(Product product) {
//        return new ProductResponse(
//                product.getId(),
//                product.getName(),
//                product.getDescription(),
//                product.getPrice(),
//                product.getSku(),
//                product.getCategory(),
//                product.getBrand(),
//                product.getDiscountedPrice(),
//                product.getStockQuantity(),
//                product.getWeight(),
//                product.getColor(),
//                product.getImageUrls(),
//                product.getTags(),
//                product.getSlug(),
//                product.getCreatedAt(),
//                product.getUpdatedAt(),
//                product.getIsActive(),
//                product.getCustomAttributes()
//        );
//    }
//}
package com.ecom.microservices.product_service.service;

import com.ecom.microservices.product_service.dto.ProductRequest;
import com.ecom.microservices.product_service.dto.ProductResponse;
import com.ecom.microservices.product_service.model.Product;
import com.ecom.microservices.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .sku(productRequest.sku())
                .category(productRequest.category())
                .brand(productRequest.brand())
                .discountedPrice(productRequest.discountedPrice())
                .stockQuantity(productRequest.stockQuantity())
                .weight(productRequest.weight())
                .color(productRequest.color())
                .imageUrls(productRequest.imageUrls())
                .tags(productRequest.tags())
                .slug(productRequest.slug())
                .customAttributes(productRequest.customAttributes())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());

        return mapToProductResponse(savedProduct);
    }

    public Page<ProductResponse> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(this::mapToProductResponse);
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new NoSuchElementException("Product not found with ID: " + id);
                });
        return mapToProductResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new NoSuchElementException("Product not found with ID: " + id);
                });

        Product updatedProduct = Product.builder()
                .id(id)
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .sku(productRequest.sku())
                .category(productRequest.category())
                .brand(productRequest.brand())
                .discountedPrice(productRequest.discountedPrice())
                .stockQuantity(productRequest.stockQuantity())
                .weight(productRequest.weight())
                .color(productRequest.color())
                .imageUrls(productRequest.imageUrls())
                .tags(productRequest.tags())
                .slug(productRequest.slug())
                .customAttributes(productRequest.customAttributes())
                .createdAt(existingProduct.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .isActive(productRequest.isActive() != null ? productRequest.isActive() : existingProduct.getIsActive())
                .build();

        Product savedProduct = productRepository.save(updatedProduct);
        log.info("Product updated successfully with ID: {}", id);

        return mapToProductResponse(savedProduct);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            log.error("Product not found with ID: {}", id);
            throw new NoSuchElementException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.getCategory(),
                product.getBrand(),
                product.getDiscountedPrice(),
                product.getStockQuantity(),
                product.getWeight(),
                product.getColor(),
                product.getImageUrls(),
                product.getTags(),
                product.getSlug(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getIsActive(),
                product.getCustomAttributes()
        );
    }
}
