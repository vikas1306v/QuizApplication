package com.vvquiz.service;

import com.vvquiz.dto.reponse.GenericResponseBean;
import com.vvquiz.dto.request.CreateQuizRequestDto;
import com.vvquiz.entities.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface QuizService {


    ResponseEntity<GenericResponseBean<Quiz>> createQuiz(CreateQuizRequestDto createQuizRequestDto);

    ResponseEntity<GenericResponseBean<Quiz>> getQuizWithAllQuestion(Integer quizId);
}
