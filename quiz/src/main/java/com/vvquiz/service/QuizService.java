package com.vvquiz.service;

import com.vvquiz.dto.request.CreateQuizRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface QuizService {
    public void createQuiz(CreateQuizRequestDto createQuizRequestDto);

}
