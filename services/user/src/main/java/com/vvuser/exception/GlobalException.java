package com.vvuser.exception;

import com.vvuser.dto.response.GenericResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<GenericResponseBean<?>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenericResponseBean.builder().message(ex.getMessage()).build());
    }
}
