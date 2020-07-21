package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.controllers.api.EdgeApi;
import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.dto.SearchPetitionDTO;
import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.ProductView;
import com.ironhack.kix.edge.service.models.views.SearchView;
import com.ironhack.kix.edge.service.models.views.UserView;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService implements EdgeApi {
    @Autowired ProductService productService;
    @Autowired SearchService searchService;
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
    public List<SearchView> searchProduct(SearchPetitionDTO petition) {
        if(petition.getImageBase64() != null && petition.getSearchText() == null
                || petition.getSearchText().isEmpty()) {
            String filter = petition.getFilter().toString().replaceAll("[{}]","");
            return searchService.searchByImage(petition.getImageBase64(), filter);
        }
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
