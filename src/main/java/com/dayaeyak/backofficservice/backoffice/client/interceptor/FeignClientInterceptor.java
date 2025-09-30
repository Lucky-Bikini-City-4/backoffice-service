package com.dayaeyak.backofficservice.backoffice.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;

    public FeignClientInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        String userId = request.getHeader("X-User-Id");
        String role = request.getHeader("X-User-Role");
        if (userId != null) template.header("X-User-Id", userId);
        if (role != null) template.header("X-User-Role", role);
    }
}
