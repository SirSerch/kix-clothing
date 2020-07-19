package com.ironhack.kix.product.service.services;

import com.ironhack.kix.product.service.controllers.api.ProductApi;
import com.ironhack.kix.product.service.exceptions.ProductNotFoundException;
import com.ironhack.kix.product.service.models.Product;
import com.ironhack.kix.product.service.models.dto.ProductDTO;
import com.ironhack.kix.product.service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService implements ProductApi {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDTO product) {
        // Create a service point to save ALL images and Return a list of IDs
        List<Long> productImages = new LinkedList<>();
        product.getProductImages().forEach((image) -> {
            isImageValid(image);
            //Do stuff with product service
        });
        return productRepository.save(new Product(product, productImages));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void updateProduct(ProductDTO updateProduct, String productId) {
        updateProduct.setProductId(this.getProductById(productId).getProductId());
        //Do some stuff with photos
        Product product = new Product(updateProduct);
        productRepository.save(product);
    }

    /**
    @Override
    public void updateProduct(ProductDTO updateProduct, String productId) {
        Product oldProduct = this.getProductById(productId);
        if (updateProduct.getProductName() != null && !updateProduct.getProductName().isEmpty())
            oldProduct.setProductName(updateProduct.getProductName());

        if (updateProduct.getProductDescription() != null && !updateProduct.getProductDescription().isEmpty())
            oldProduct.setProductDescription(updateProduct.getProductDescription());

        if(updateProduct.getProductPrice() != null)
            oldProduct.setProductPrice(updateProduct.getProductPrice());

        if (updateProduct.getProductImages() != null && !updateProduct.getProductImages().isEmpty()) {
            //Do stuff with photos;
        }

        if (updateProduct.getProductTags() != null && !updateProduct.getProductTags().isEmpty()) {
            //Do stuff with tags
            oldProduct.setProductTags(updateProduct.getProductTags());
        }

        productRepository.save(oldProduct);
    }
     **/

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(this.getProductById(productId).getProductId());
    }

    private boolean isImageValid(String imageBase64) {
        return imageBase64.matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$");
    }
}
