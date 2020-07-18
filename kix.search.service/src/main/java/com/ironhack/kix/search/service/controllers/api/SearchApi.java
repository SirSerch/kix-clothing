package com.ironhack.kix.search.service.controllers.api;

import com.ironhack.kix.search.service.models.Product;

import java.util.List;

public interface SearchApi {
    List<Product> getProductsByImage(String imageBase64);
    Product getProductByImage(String imageBase64);
    Product postProduct(Product product);
    void updateProduct(Product product, Long productId);
    void deleteProduct(Long productId);
}
