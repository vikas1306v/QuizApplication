package com.vvquiz.exception;

public class QuestionServiceNotFoundException extends RuntimeException{
    public QuestionServiceNotFoundException(String message) {
        super(message);
    }
}
