package com.dayaeyak.backofficservice.backoffice.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@AllArgsConstructor
@ToString
public class ResourceScope {
    private final Long sellerId;

    public static ResourceScope of(Long sellerId) {
        return new ResourceScope(sellerId);
    }

    public static Optional<ResourceScope> optionalOf(Long sellerId) {
        return Optional.ofNullable(sellerId).map(ResourceScope::new);
    }
}

