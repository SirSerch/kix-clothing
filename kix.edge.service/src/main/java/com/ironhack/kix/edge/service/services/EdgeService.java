package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.controllers.api.EdgeApi;
import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.dto.SearchDTO;
import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.ProductView;
import com.ironhack.kix.edge.service.models.views.SearchView;
import com.ironhack.kix.edge.service.models.views.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService implements EdgeApi {

    static final Logger LOGGER = LoggerFactory.getLogger(EdgeService.class);

    @Autowired ProductService productService;
    @Autowired UserService userService;

    @Override
    public List<ProductView> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public UserView createNewUser(UserDTO user) {
        return userService.createNewUser(user);
    }

    @Override
    public List<UserView> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserView getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userService.deleteUserById(id);
    }

    @Override
    public ProductView createNewProduct(ProductDTO product) {
        return productService.createNewProduct(product);
    }

    @Override
    public ProductView getProductById(String productId) {
        return productService.getProductById(productId);
    }

    @Override
    public void deleteProductById(String productId) {
        productService.deleteProductById(productId);
    }


    @Override
    public void updateProduct(ProductDTO productDTO, String productId) {
        productService.updateProduct(productDTO, productId);
    }

    @Override
    public List<ProductView> searchProduct(SearchDTO petition) {
        LOGGER.info(petition.toString());
        if(petition.isImageSearch())
            return productService.searchProductsByImage(petition);
        return null;
    }



    @Override
    public ProductView indexProductOnSearchEngine(String productId) {
        return productService.indexProductOnSearchEngine(productId);
    }

    @Override
    public ProductView deleteIndexFromSearchEngine(String productId) {
        return productService.deleteIndexFromSearchEngine(productId);
    }
}
