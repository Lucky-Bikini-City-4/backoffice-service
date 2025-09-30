package com.dayaeyak.backofficservice.backoffice.client.service;

import com.dayaeyak.backofficservice.backoffice.application.dtos.ApplicationResponseDto;
import com.dayaeyak.backofficservice.backoffice.client.dtos.CreatePerformanceRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "performance-service")
public interface PerformanceServiceClient {

    @PostMapping("/performances")
    void registerPerformance(
            @RequestBody CreatePerformanceRequestDto dto
    );
}
