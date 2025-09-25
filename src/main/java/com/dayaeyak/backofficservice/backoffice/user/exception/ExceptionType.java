package com.dayaeyak.backofficservice.backoffice.user.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionType {

    HttpStatus getStatus();
    String getMessage();
}
