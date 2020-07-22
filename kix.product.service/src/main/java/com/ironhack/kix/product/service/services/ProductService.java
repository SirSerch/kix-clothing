package com.ironhack.kix.product.service.services;

import com.ironhack.kix.product.service.clients.ImageClient;
import com.ironhack.kix.product.service.clients.SearchClient;
import com.ironhack.kix.product.service.controllers.api.ProductApi;
import com.ironhack.kix.product.service.exceptions.ProductNotFoundException;
import com.ironhack.kix.product.service.models.Product;
import com.ironhack.kix.product.service.models.dto.*;
import com.ironhack.kix.product.service.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductApi {

    static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired ProductRepository productRepository;
    @Autowired ImageClient imageClient;
    @Autowired SearchClient searchClient;
    @Autowired SearchService searchService;

    @Override
    @Transactional
    public ProductView createProduct(ProductDTO productDTO) {
        LOGGER.info("Creating new Product");
        GalleryView galleryView = imageClient.createNewGallery(new GalleryDTO(productDTO.getProductImages()));
        return new ProductView(productRepository.save(new Product(productDTO, galleryView.getGalleryId())), galleryView);
    }

    @Override
    public List<ProductView> getAllProducts() {
        LOGGER.info("Getting all products");
        List<ProductView> productViews = new LinkedList<>();
        List<Product> products = productRepository.findAll();
        products.forEach((product -> {
            productViews.add(new ProductView(product, imageClient.getGalleryById(product.getProductGallery())));
        }));
        return productViews;
    }

    @Override
    public ProductView getProductById(String productId) {
        LOGGER.info("Get product by id: " + productId);
        Product product = this.getProductByProductId(productId);
        GalleryView galleryView = imageClient.getGalleryById(product.getProductGallery());
        return new ProductView(product, galleryView);
    }

    public ProductView getProductById(ImageSearchResult result){
        ProductView product = this.getProductById(result.getProductId());
        product.setScore(result.getScore());
        return product;
    }

    @Override
    public void updateProduct(ProductDTO updateProduct, String productId) {
        Product product = this.getProductByProductId(productId);
        imageClient.deleteAllImagesByGalleryId(product.getProductGallery());
        GalleryView gallery = imageClient.createNewGallery(new GalleryDTO(updateProduct.getProductImages()));
        updateProduct.setProductId(productId);
        Product newProduct = new Product(updateProduct, gallery.getGalleryId());
        productRepository.save(newProduct);
    }

    @Override
    public ProductView indexProduct(String productId) {
        Product product = this.getProductByProductId(productId);
        GalleryView gallery = imageClient.getGalleryById(product.getProductGallery());
        IndexView indexView = searchClient.indexProduct(new ProductView(product, gallery));
        product.setIndexed(true);
        product.setLastIndexedTime(indexView.getIndexedTime());
        return new ProductView(productRepository.save(product), gallery);
    }

    @Override
    public ProductView deleteIndexProduct(String productId) {
        Product product = this.getProductByProductId(productId);
        GalleryView gallery = imageClient.getGalleryById(product.getProductGallery());
        searchClient.deleteIndexedProduct(productId);
        product.setIndexed(false);
        product.setLastIndexedTime(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC));
        return new ProductView(productRepository.save(product), gallery);
    }

    @Override
    @Transactional
    public void deleteProduct(String productId) {
        Product product = this.getProductByProductId(productId);
        searchClient.deleteIndexedProduct(productId);
        imageClient.deleteAllImagesByGalleryId(product.getProductGallery());
        productRepository.deleteById(this.getProductById(productId).getProductId());
    }

    public List<ProductView> searchProduct(SearchDTO searchDTO){
        Map<String, String> filter;
        if(searchDTO.getFilter() != null){
            filter = searchDTO.getFilter();
        } else {
            filter = new HashMap<>();
        }
        if(searchDTO.getImageSearch()){
            List<ImageSearchResult> result = searchService.searchByImage(searchDTO.getSearch(), filter);
            return result.stream().map(this::getProductById).collect(Collectors.toList());
        }
        return null;
    }

    private Product getProductByProductId(String productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    /**
     * @Override public void updateProduct(ProductDTO updateProduct, String productId) {
     * Product oldProduct = this.getProductById(productId);
     * if (updateProduct.getProductName() != null && !updateProduct.getProductName().isEmpty())
     * oldProduct.setProductName(updateProduct.getProductName());
     * <p>
     * if (updateProduct.getProductDescription() != null && !updateProduct.getProductDescription().isEmpty())
     * oldProduct.setProductDescription(updateProduct.getProductDescription());
     * <p>
     * if(updateProduct.getProductPrice() != null)
     * oldProduct.setProductPrice(updateProduct.getProductPrice());
     * <p>
     * if (updateProduct.getProductImages() != null && !updateProduct.getProductImages().isEmpty()) {
     * //Do stuff with photos;
     * }
     * <p>
     * if (updateProduct.getProductTags() != null && !updateProduct.getProductTags().isEmpty()) {
     * //Do stuff with tags
     * oldProduct.setProductTags(updateProduct.getProductTags());
     * }
     * <p>
     * productRepository.save(oldProduct);
     * }
     **/
}
