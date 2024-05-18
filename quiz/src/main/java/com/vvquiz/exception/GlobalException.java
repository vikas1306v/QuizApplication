package com.vvquiz.exception;

import com.vvquiz.utils.ErrorMessageAndCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(QuizAppException.class)
    public ResponseEntity<ErrorMessageAndCode> handleQuizAppException(QuizAppException e){
        return ResponseEntity.status(400).body(new ErrorMessageAndCode(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
