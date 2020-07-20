package com.ironhack.kix.edge.service.repositories.clients;

import com.ironhack.kix.edge.service.models.dto.SearchPetitionDTO;
import com.ironhack.kix.edge.service.models.views.ImageSearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("kix-search-service")
public interface SearchClient {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    List<ImageSearchResult> searchProductsByImage(@RequestBody String imageBase64, @RequestParam(name= "filter", required = false, defaultValue = "") String filter);

}
