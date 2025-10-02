package com.dayaeyak.backofficservice.backoffice.client.service;

import com.dayaeyak.backofficservice.backoffice.client.dtos.RestaurantRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="restaurant-service")
public interface RestaurantServiceClient {
    @PostMapping("/restaurants")
    void registerRestaurant(
            @RequestBody RestaurantRequestDto dto
    );
}
