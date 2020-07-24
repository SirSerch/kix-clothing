package com.ironhack.kix.product.service.repositories;

import com.ironhack.kix.product.service.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
