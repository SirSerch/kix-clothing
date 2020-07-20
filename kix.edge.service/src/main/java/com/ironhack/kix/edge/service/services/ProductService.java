package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.views.GalleryView;
import com.ironhack.kix.edge.service.models.views.ProductView;
import com.ironhack.kix.edge.service.repositories.clients.ProductClient;
import com.netflix.appinfo.InstanceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired private DiscoveryClient discoveryClient;
    @Autowired private ProductClient productClient;

    protected ProductView createNewProduct(ProductDTO product) {
        return productClient.createProduct(product);
    }

    protected ProductView getProductById(String productId) {
        LOGGER.info("Get ProductView from: " + productId);
        return productClient.getProductById(productId);
    }

    protected void deleteProductById(String productId) {
        productClient.deleteProduct(productId);
    }

    protected ProductView indexProductOnSearchEngine(String productId) {
        return productClient.indexProduct(productId);
    }

    protected ProductView deleteIndexFromSearchEngine(String productId) {
        return productClient.deleteIndexProduct(productId);
    }

}
