package com.ironhack.kix.product.service.controllers.api;

import com.ironhack.kix.product.service.models.Product;
import com.ironhack.kix.product.service.models.dto.ProductDTO;
import com.ironhack.kix.product.service.models.dto.ProductView;

import java.util.List;

public interface ProductApi {
    ProductView createProduct(ProductDTO product);
    List<ProductView> getAllProducts();
    ProductView getProductById(String productId);
    void updateProduct(ProductDTO updateProduct, String productId);
    void deleteProduct(String productId);
    ProductView indexProduct(String productId);
    ProductView deleteIndexProduct(String productId);
}
