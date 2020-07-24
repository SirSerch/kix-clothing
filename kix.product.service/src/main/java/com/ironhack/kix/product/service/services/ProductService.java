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


    /**
     * Creates a product using a DTO to pass the data
     * @param productDTO
     * @return
     */
    @Override
    @Transactional
    public ProductView createProduct(ProductDTO productDTO) {
        LOGGER.info("Creating new Product");
        GalleryView galleryView = imageClient.createNewGallery(new GalleryDTO(productDTO.getProductImages()));
        return new ProductView(productRepository.save(new Product(productDTO, galleryView.getGalleryId())), galleryView);
    }

    /**
     * Retrieve all products in the Mongo Database DB
     * @return
     */
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

    /**
     * Get a ProductView using by Id
     * @param productId
     * @return
     */
    @Override
    public ProductView getProductById(String productId) {
        LOGGER.info("Get product by id: " + productId);
        Product product = this.getProductByProductId(productId);
        GalleryView galleryView = imageClient.getGalleryById(product.getProductGallery());
        return new ProductView(product, galleryView);
    }

    /**
     * Get a product
     * @param result
     * @return
     */
    public ProductView getProductById(ImageSearchResult result){
        ProductView product = this.getProductById(result.getProductId());
        product.setScore(result.getScore());
        return product;
    }

    /**
     * Update a product using a ProductDTO and ID
     * @param updateProduct ProductDTO
     * @param productId
     */
    @Override
    public void updateProduct(ProductDTO updateProduct, String productId) {
        Product product = this.getProductByProductId(productId);
        searchClient.deleteIndexedProduct(productId);
        imageClient.deleteAllImagesByGalleryId(product.getProductGallery());
        GalleryView gallery = imageClient.createNewGallery(new GalleryDTO(updateProduct.getProductImages()));
        updateProduct.setProductId(productId);
        Product newProduct = new Product(updateProduct, gallery.getGalleryId());
        productRepository.deleteById(productId);
        productRepository.save(newProduct);
    }

    /**
     * Index a product in the Search Engine. It takes as a parameter the id of the product and looks for the images
     * associated with it to pass them to the feign client
     * @param productId
     * @return
     */
    @Override
    public ProductView indexProduct(String productId) {
        LOGGER.info("Index product");
        Product product = this.getProductByProductId(productId);
        LOGGER.info("Indexing :" + product.toString());
        GalleryView gallery = imageClient.getGalleryById(product.getProductGallery());
        LOGGER.info(gallery.toString());
        IndexView indexView = searchClient.indexProduct(new ProductView(product, gallery));
        product.setIndexed(true);
        product.setLastIndexedTime(indexView.getIndexedTime());
        LOGGER.info("Product: " + product.toString());
        return new ProductView(productRepository.save(product), gallery);
    }

    /**
     * It removes the index of the product but not its images, when we modify the images or the information
     * of some product we have to re-index.
     * @param productId
     * @return
     */
    @Override
    public ProductView deleteIndexProduct(String productId) {
        Product product = this.getProductByProductId(productId);
        GalleryView gallery = imageClient.getGalleryById(product.getProductGallery());
        LOGGER.info("Delete indexing, gallery: " + gallery.toString());
        searchClient.deleteIndexedProduct(productId);
        product.setIndexed(false);
        product.setLastIndexedTime(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC));
        return new ProductView(productRepository.save(product), gallery);
    }

    /**
     * Delete a whole product, first delete its index from the server, its images and finally the product itself
     * @param productId
     */
    @Override
    @Transactional
    public void deleteProduct(String productId) {
        Product product = this.getProductByProductId(productId);
        searchClient.deleteIndexedProduct(productId);
        imageClient.deleteAllImagesByGalleryId(product.getProductGallery());
        productRepository.deleteById(productId);
    }

    /**
     * Search for a product, it can be either a product search by keywords (to be implemented)
     * or by image using the Google Vision image search system
     * @param searchDTO
     * @return
     */
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


    /**
     * Private method that manages the search of products by ID and returns an exception in case it is not found
     * @param productId
     * @return
     */
    private Product getProductByProductId(String productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

}
