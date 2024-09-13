package com.vvsubject.exception;

import com.vvsubject.dto.response.GenericResponseBean;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SubjectAppException.class)
    public ResponseEntity<GenericResponseBean<?>> handleSubjectAppException(SubjectAppException e) {
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(GenericResponseBean.builder().message(e.getMessage()).status(false).build());
    }
}
