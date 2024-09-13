package com.vvquiz.service;

import com.vvquiz.dto.response.GenericResponseBean;
import com.vvquiz.entity.Answer;
import com.vvquiz.exception.AnswerServiceNotFoundException;
import com.vvquiz.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    public ResponseEntity<GenericResponseBean<Answer>> getAnswer(String questionId) {
        answerRepository.findByQuestionId(questionId).orElseThrow(()->new AnswerServiceNotFoundException("Answer not found"));
    }


}
