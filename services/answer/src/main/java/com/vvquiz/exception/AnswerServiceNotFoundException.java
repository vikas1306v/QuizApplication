package com.vvquiz.exception;

public class AnswerServiceNotFoundException extends RuntimeException{
    public AnswerServiceNotFoundException(String message) {
        super(message);
    }
}
