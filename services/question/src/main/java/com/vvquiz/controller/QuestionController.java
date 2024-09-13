package com.vvquiz.controller;

import com.vvquiz.dto.request.QuestionCreateRequestDto;
import com.vvquiz.dto.response.AllQuestionOfQuizResponseDto;
import com.vvquiz.dto.response.GenericResponseBean;
import com.vvquiz.dto.response.QuestionResponseDto;
import com.vvquiz.service.impl.QuestionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionServiceImpl questionService;
    @PostMapping
    public ResponseEntity<GenericResponseBean<?>> createQuestion(@RequestBody @Valid QuestionCreateRequestDto requestDto) {
        return questionService.createQuestion(requestDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseBean<QuestionResponseDto>> getQuestions(@PathVariable Integer id) {
        return questionService.getQuestions(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseBean<?>> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }
    //find ll question of a quiz
    @GetMapping("/quiz/{id}")
    public ResponseEntity<GenericResponseBean<AllQuestionOfQuizResponseDto>> getQuestionsByQuizId(@PathVariable Integer id) {
        return questionService.getQuestionsByQuizId(id);
    }


}
