package com.vvquiz.controller;

import com.vvquiz.annotation.CheckRole;
import com.vvquiz.dto.reponse.GenericResponseBean;
import com.vvquiz.dto.request.CreateQuizRequestDto;
import com.vvquiz.entities.Quiz;
import com.vvquiz.service.impl.QuizServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private  final QuizServiceImpl quizService;

    @PostMapping
    @CheckRole("STUDENT")
    public ResponseEntity<GenericResponseBean<Quiz>> createQuiz(@RequestBody @Valid CreateQuizRequestDto createQuizRequestDto) {
       return quizService.createQuiz(createQuizRequestDto);
    }
    //get all question of quiz
    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<GenericResponseBean<Quiz>> getAllQuiz(@PathVariable("quizId") Integer quizId) {
        return quizService.getQuizWithAllQuestion(quizId);
    }
}
