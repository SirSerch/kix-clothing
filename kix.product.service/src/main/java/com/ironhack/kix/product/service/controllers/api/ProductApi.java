package com.ironhack.kix.product.service.controllers.api;

import com.ironhack.kix.product.service.models.Product;
import com.ironhack.kix.product.service.models.dto.ProductDTO;

import java.util.List;

public interface ProductApi {
    Product createProduct(ProductDTO product);
    List<Product> getAllProducts();
    Product getProductById(String productId);
    void updateProduct(ProductDTO updateProduct, String productId);
    void deleteProduct(String productId);
}
