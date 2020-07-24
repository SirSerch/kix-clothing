package com.ironhack.kix.edge.service.controllers.api;

import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.dto.SearchDTO;
import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.ProductView;
import com.ironhack.kix.edge.service.models.views.UserView;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EdgeApi {
    UserView createNewUser(UserDTO user);
    List<UserView> getAllUsers();
    UserView getUserById(Long id);
    void deleteUserById(Long id);

    ProductView createNewProduct(ProductDTO product);
    ProductView getProductById(String productId);
    List<ProductView> getAllProducts();
    void deleteProductById(String productId);
    void updateProduct(ProductDTO productDTO, String productId);

    List<ProductView> searchProduct(SearchDTO petition);
    ProductView indexProductOnSearchEngine(String productId);
    ProductView deleteIndexFromSearchEngine(String productId);
    void updateUser(@RequestBody UserDTO user, Long userId);


}
