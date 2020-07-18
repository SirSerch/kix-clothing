package com.ironhack.kix.search.service.services;

import com.ironhack.kix.search.service.clients.ImageClient;
import com.ironhack.kix.search.service.controllers.api.SearchApi;
import com.ironhack.kix.search.service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements SearchApi {
    @Autowired
    ImageClient imageClient;
    @Autowired GoogleService googleService;

    @Override
    public List<Product> getProductsByImage(String imageBase64) {
        return null;
    }

    @Override
    public Product getProductByImage(String imageBase64) {
        return null;
    }

    @Override
    public Product postProduct(Product product) {
        return null;
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
