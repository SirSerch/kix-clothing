package com.ironhack.kix.search.service.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("kix-image-service")
public interface ImageClient {
}
