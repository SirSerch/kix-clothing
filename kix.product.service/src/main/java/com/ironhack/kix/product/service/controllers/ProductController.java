package com.ironhack.kix.product.service.controllers;

import com.ironhack.kix.product.service.controllers.api.ProductApi;
import com.ironhack.kix.product.service.models.Product;
import com.ironhack.kix.product.service.models.dto.ProductDTO;
import com.ironhack.kix.product.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController implements ProductApi {
    @Autowired
    ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public Product createProduct(@RequestBody ProductDTO product) {
        return productService.createProduct(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    public Product getProductById(@PathVariable(name = "productId") String productId) {
        return productService.getProductById(productId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT, value = "/products/{productId}")
    public void updateProduct(@RequestBody ProductDTO product, @PathVariable(name = "productId") String productId) {
        productService.updateProduct(product, productId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    public void deleteProduct(@PathVariable(name = "productId") String productId) {
        productService.deleteProduct(productId);
    }
}
