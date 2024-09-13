package com.vvquiz.controller;

import com.vvquiz.dto.response.GenericResponseBean;
import com.vvquiz.entity.Answer;
import com.vvquiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    @GetMapping("/{questionId}")
    public ResponseEntity<GenericResponseBean<Answer>> getAnswer(@PathVariable String questionId) {
        return answerService.getAnswer(questionId);
    }
}
