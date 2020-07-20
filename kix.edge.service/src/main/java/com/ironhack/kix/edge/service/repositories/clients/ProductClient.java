package com.ironhack.kix.edge.service.repositories.clients;

import com.ironhack.kix.edge.service.models.dto.ProductDTO;
import com.ironhack.kix.edge.service.models.views.ProductView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("kix-product-service")
public interface ProductClient {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/product")
    ProductView createProduct(@RequestBody ProductDTO product);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    List<ProductView> getAllProducts();

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    ProductView getProductById(@PathVariable(name = "productId") String productId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT, value = "/products/{productId}")
    void updateProduct(@RequestBody ProductDTO product, @PathVariable(name = "productId") String productId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    void deleteProduct(@PathVariable(name = "productId") String productId);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/products/{productId}/indexing")
    ProductView indexProduct(@PathVariable(name = "productId") String productId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}/indexing")
    ProductView deleteIndexProduct(@PathVariable(name = "productId") String productId);
}
