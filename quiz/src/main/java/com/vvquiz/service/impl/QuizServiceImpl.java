package com.vvquiz.service.impl;

import com.vvquiz.dto.request.CreateQuizRequestDto;
import com.vvquiz.entities.Quiz;
import com.vvquiz.exception.QuizAppException;
import com.vvquiz.repository.QuizRepository;
import com.vvquiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository  quizRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createQuiz(CreateQuizRequestDto createQuizRequestDto) {
        try{
            Quiz quiz = modelMapper.map(createQuizRequestDto, Quiz.class);
            quizRepository.save(quiz);
        }catch (Exception e){
            throw new QuizAppException("Error while creating quiz");
        }
    }
}
