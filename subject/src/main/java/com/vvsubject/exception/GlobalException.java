package com.vvsubject.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SubjectAppException.class)
    public String handleSubjectAppException(SubjectAppException e) {
        return e.getMessage();
    }
}
