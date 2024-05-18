package com.vvquiz.controller;

import com.vvquiz.dto.request.CreateQuizRequestDto;
import com.vvquiz.service.impl.QuizServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private  final QuizServiceImpl quizService;

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestBody CreateQuizRequestDto createQuizRequestDto) {
       return null;
    }
}
