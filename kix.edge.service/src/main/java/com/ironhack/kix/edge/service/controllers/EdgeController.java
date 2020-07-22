package com.ironhack.kix.edge.service.controllers;

import com.ironhack.kix.edge.service.controllers.api.EdgeApi;
import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.dto.SearchDTO;
import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.views.ProductView;
import com.ironhack.kix.edge.service.models.views.SearchView;
import com.ironhack.kix.edge.service.models.views.UserView;
import com.ironhack.kix.edge.service.services.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EdgeController implements EdgeApi {

    @Autowired private EdgeService edgeService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public UserView createNewUser(@RequestBody UserDTO user) {
        return edgeService.createNewUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserView> getAllUsers() {
        return edgeService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserView getUserById(@PathVariable("userId") Long userId) {
        return edgeService.getUserById(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId) {
        edgeService.deleteUserById(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public ProductView createNewProduct(@RequestBody ProductDTO product) {
        return edgeService.createNewProduct(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    public ProductView getProductById(@PathVariable("productId") String productId) {
        return edgeService.getProductById(productId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<ProductView> getAllProducts() {
        return edgeService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    public void deleteProductById(@PathVariable("productId") String productId) {
        edgeService.deleteProductById(productId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.POST, value = "/products/{productId}")
    public List<ProductView> searchProduct(@RequestBody SearchDTO petition) {
        return edgeService.searchProduct(petition);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/products/{productId}/indexing")
    public ProductView indexProductOnSearchEngine(@PathVariable("productId") String productId) {
        return edgeService.indexProductOnSearchEngine(productId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}/indexing")
    public ProductView deleteIndexFromSearchEngine(@PathVariable("productId") String productId) {
        return edgeService.deleteIndexFromSearchEngine(productId);
    }
}
