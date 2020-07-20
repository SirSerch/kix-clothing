package com.ironhack.kix.edge.service.controllers.api;

import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.dto.SearchPetitionDTO;
import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.ProductView;
import com.ironhack.kix.edge.service.models.views.SearchView;
import com.ironhack.kix.edge.service.models.views.UserView;

import java.util.List;

public interface EdgeApi {
    UserView createNewUser(UserDTO user);
    List<UserView> getAllUsers();
    UserView getUserById(Long id);
    void deleteUserById(Long id);

    ProductView createNewProduct(ProductDTO product);
    ProductView getProductById(String productId);
    void deleteProductById(String productId);

    List<SearchView> searchProduct(SearchPetitionDTO petition);
    ProductView indexProductOnSearchEngine(String productId);
    ProductView deleteIndexFromSearchEngine(String productId);
}
