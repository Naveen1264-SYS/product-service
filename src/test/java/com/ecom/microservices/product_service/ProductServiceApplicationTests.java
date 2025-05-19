//package com.ecom.microservices.product_service;
//
//import io.restassured.RestAssured;
//import org.hamcrest.Matcher;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.springframework.context.annotation.Import;
//import org.testcontainers.containers.MongoDBContainer;
//
//@Import(TestcontainersConfiguration.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ProductServiceApplicationTests {
//
//
//	@ServiceConnection
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
//
//	@LocalServerPort
//	private Integer port;
//
//	@BeforeEach
//	void setUp() {
//		RestAssured.baseURI = "http://localhost";
//		RestAssured.port = port;
//	}
//	static
//{
//		mongoDBContainer.start();
//	}
//	@Test
//	void shouldCreateProduct() {
//		String requestBody = """
//				{
//				  "name": "Wireless Mouse",
//				  "description": "A high-precision wireless mouse with ergonomic design.",
//				  "price": 2900
//				}
//				""";
//
//		RestAssured.given()
//				.contentType("application/json")
//				.body(requestBody)
//				.when()
//				.post("/api/product")
//				.then()
//				.statusCode(201)
//				.body("id", Matchers.notNullValue())
//				.body("name", Matchers.equalTo("Wireless Mouse"))
//				.body("description", Matchers.equalTo("A high-precision wireless mouse with ergonomic design."))
//				.body("price", Matchers.equalTo(2900));
//
//
//	}
//}
package com.ecom.microservices.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
                {
                  "name": "Running Shoes",
                  "description": "Lightweight and breathable running shoes",
                  "price": 89.99,
                  "sku": "SHO123",
                  "category": "Footwear",
                  "brand": "FitPulse",
                  "discountedPrice": 79.99,
                  "stockQuantity": 120,
                  "weight": 0.5,
                  "color": "Blue",
                  "imageUrls": ["http://example.com/image5.jpg", "http://example.com/image6.jpg"],
                  "tags": ["running", "shoes", "athletic"],
                  "slug": "running-shoes-fitpulse",
                  "customAttributes": {
                    "warranty": "6 months",
                    "sizeRange": "US 7-11"
                  }
                }
                """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Running Shoes"))
				.body("description", Matchers.equalTo("Lightweight and breathable running shoes"))
				.body("price", Matchers.equalTo(89.99f))
				.body("sku", Matchers.equalTo("SHO123"))
				.body("category", Matchers.equalTo("Footwear"))
				.body("brand", Matchers.equalTo("FitPulse"))
				.body("discountedPrice", Matchers.equalTo(79.99f))
				.body("stockQuantity", Matchers.equalTo(120))
				.body("weight", Matchers.equalTo(0.5f))
				.body("color", Matchers.equalTo("Blue"))
				.body("imageUrls", Matchers.containsInAnyOrder("http://example.com/image5.jpg", "http://example.com/image6.jpg"))
				.body("tags", Matchers.containsInAnyOrder("running", "shoes", "athletic"))
				.body("slug", Matchers.equalTo("running-shoes-fitpulse"))
				.body("customAttributes.warranty", Matchers.equalTo("6 months"))
				.body("customAttributes.sizeRange", Matchers.equalTo("US 7-11"))
				.body("createdAt", Matchers.notNullValue())
				.body("updatedAt", Matchers.notNullValue())
				.body("isActive", Matchers.equalTo(true));
	}
}
