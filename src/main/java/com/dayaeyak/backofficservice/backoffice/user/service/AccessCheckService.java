package com.dayaeyak.backofficservice.backoffice.user.service;

import com.dayaeyak.backofficservice.backoffice.application.entity.Application;
import com.dayaeyak.backofficservice.backoffice.application.repository.ApplicationRepository;
import com.dayaeyak.backofficservice.backoffice.common.exception.BusinessException;
import com.dayaeyak.backofficservice.backoffice.common.exception.ErrorCode;
import com.dayaeyak.backofficservice.backoffice.common.security.AccessGuard;
import com.dayaeyak.backofficservice.backoffice.common.security.Action;
import com.dayaeyak.backofficservice.backoffice.common.security.ResourceScope;
import com.dayaeyak.backofficservice.backoffice.user.dto.Passport;
import com.dayaeyak.backofficservice.backoffice.user.enums.UserRole;
import com.dayaeyak.backofficservice.backoffice.user.exception.CommonExceptionType;
import com.dayaeyak.backofficservice.backoffice.user.exception.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccessCheckService {

    private final ApplicationRepository applicationRepository;

    public void checkPermission(Passport passport, Action action, UserRole[] roles, Long objectId) {
        ResourceScope scope = null;
        if (objectId != null) {
            Application app = applicationRepository.findByIdAndDeletedAtIsNull(objectId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.APPLICATION_NOT_FOUND));
            log.info("Application found: id={}, sellerId={}", app.getId(), app.getSellerId());

            // sellerId null이면 바로 예외 발생
            if (app.getSellerId() == null) {
                throw new BusinessException(ErrorCode.INVALID_INPUT, "해당 신청서의 판매자 아이디가 필요합니다.");
            }
            scope = ResourceScope.of(app.getSellerId());
            log.info("ResourceScope set with sellerId={}", app.getSellerId());
        }

        log.info("Checking roles: requiredRoles={}, userRole={}", Arrays.toString(roles), passport.role());
        // roles 필터링: 필요하면 AccessGuard 내부에서 검사
        if (roles.length != 0 && Arrays.stream(roles).noneMatch(r -> r == passport.role())) {
            throw new CustomRuntimeException(CommonExceptionType.REQUEST_ACCESS_DENIED);
        }
        log.info("Calling AccessGuard.requiredPermission - action={}, userId={}, role={}, scope={}",
                action, passport.userId(), passport.role(), scope);
        AccessGuard.requiredPermission(action, passport.userId(), passport.role(), Optional.ofNullable(scope));
    }
}
