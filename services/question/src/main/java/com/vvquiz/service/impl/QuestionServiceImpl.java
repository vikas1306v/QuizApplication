package com.vvquiz.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vvquiz.dto.request.QuestionCreateRequestDto;
import com.vvquiz.dto.response.AllQuestionOfQuizResponseDto;
import com.vvquiz.dto.response.GenericResponseBean;
import com.vvquiz.dto.response.QuestionResponseDto;
import com.vvquiz.entities.Question;
import com.vvquiz.exception.QuestionServiceNotFoundException;
import com.vvquiz.repository.QuestionRepository;
import com.vvquiz.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public ResponseEntity<GenericResponseBean<?>> createQuestion(QuestionCreateRequestDto requestDto) {
        Question question= questionDtoToEntity(requestDto);
        question.setCreatedAt(java.time.LocalDateTime.now());
        question.setUpdatedAt(java.time.LocalDateTime.now());
        questionRepository.save(question);
        return ResponseEntity.ok(GenericResponseBean.builder().data(question).message("Question created successfully").status(true).build());
    }

    @Override
    public ResponseEntity<GenericResponseBean<QuestionResponseDto>> getQuestions(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionServiceNotFoundException("Question not found"));
        return ResponseEntity.ok(GenericResponseBean.<QuestionResponseDto>builder().data(objectMapper.convertValue(question, QuestionResponseDto.class)).message("Question fetched successfully").status(true).build());
    }

    @Override
    @Transactional
    public ResponseEntity<GenericResponseBean<?>> deleteQuestion(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionServiceNotFoundException("Question not found"));
        questionRepository.delete(question);
        return ResponseEntity.ok(GenericResponseBean.builder().message("Question deleted successfully").status(true).build());
    }

    @Override
    public ResponseEntity<GenericResponseBean<AllQuestionOfQuizResponseDto>> getQuestionsByQuizId(Integer id) {
        List<Question> allByQuizId = questionRepository.findAllByQuizId(id);
        List<QuestionResponseDto> requestDto = allByQuizId.stream().map((q) -> objectMapper.convertValue(q, QuestionResponseDto.class)).toList();
        AllQuestionOfQuizResponseDto allQuestionOfQuizResponseDto = AllQuestionOfQuizResponseDto.builder().questions(requestDto).build();
        return ResponseEntity.ok(GenericResponseBean.<AllQuestionOfQuizResponseDto>builder().data(allQuestionOfQuizResponseDto).message("Questions fetched successfully").status(true).build());
    }

    private Question questionDtoToEntity(QuestionCreateRequestDto requestDto) {
        return objectMapper.convertValue(requestDto, Question.class);
    }
}