package com.ironhack.kix.search.service.clients;

import com.ironhack.kix.search.service.models.ProductView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("kix-product-service")
public interface ProductClient {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    ProductView getProductById(@PathVariable(name = "productId") String productId);
}
