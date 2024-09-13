package com.vvquiz.service;

import com.vvquiz.dto.request.QuestionCreateRequestDto;
import com.vvquiz.dto.response.AllQuestionOfQuizResponseDto;
import com.vvquiz.dto.response.GenericResponseBean;
import com.vvquiz.dto.response.QuestionResponseDto;
import org.springframework.http.ResponseEntity;

public interface QuestionService {


    ResponseEntity<GenericResponseBean<?>> createQuestion(QuestionCreateRequestDto requestDto);

    ResponseEntity<GenericResponseBean<QuestionResponseDto>> getQuestions(Integer id);
    ResponseEntity<GenericResponseBean<?>> deleteQuestion(Integer id);

    ResponseEntity<GenericResponseBean<AllQuestionOfQuizResponseDto>> getQuestionsByQuizId(Integer id);
}
