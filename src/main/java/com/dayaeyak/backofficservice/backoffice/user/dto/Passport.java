package com.dayaeyak.backofficservice.backoffice.user.dto;


import com.dayaeyak.backofficservice.backoffice.user.enums.UserRole;

public record Passport(
        Long userId,
        UserRole role
) {
}
