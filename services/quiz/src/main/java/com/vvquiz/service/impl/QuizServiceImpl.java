package com.vvquiz.service.impl;

import com.vvquiz.dto.reponse.AllQuestionOfQuizResponseDto;
import com.vvquiz.dto.reponse.GenericResponseBean;
import com.vvquiz.dto.request.CreateQuizRequestDto;
import com.vvquiz.entities.Question;
import com.vvquiz.entities.Quiz;
import com.vvquiz.exception.QuizAppException;
import com.vvquiz.repository.QuizRepository;
import com.vvquiz.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository  quizRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;


    @Override
    @Transactional
    public ResponseEntity<GenericResponseBean<Quiz>> createQuiz(CreateQuizRequestDto createQuizRequestDto) {
        Quiz quiz = modelMapper.map(createQuizRequestDto, Quiz.class);
        String code=UUID.randomUUID().toString();
        quiz.setQuizCode(code.substring(0,4).toUpperCase());
        Quiz save = quizRepository.save(quiz);
        return ResponseEntity.status(201).body(GenericResponseBean.<Quiz>builder().status(true).data(save).message("quiz Created Successfully").build());
    }

    @Override
    public ResponseEntity<GenericResponseBean<Quiz>> getQuizWithAllQuestion(Integer quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizAppException("Quiz not found"));
        String url = "http://QUESTION-SERVICE/question/quiz/" + quizId;
        ResponseEntity<GenericResponseBean<AllQuestionOfQuizResponseDto>> responseEntity =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<GenericResponseBean<AllQuestionOfQuizResponseDto>>() {});
        GenericResponseBean<AllQuestionOfQuizResponseDto> response = responseEntity.getBody();
        List<Question> questions = getList(response);
        quiz.setQuestions(questions);
        GenericResponseBean<Quiz> responseBean = GenericResponseBean.<Quiz>builder()
                .status(true)
                .data(quiz)
                .message("Quiz fetched successfully with questions")
                .build();
        return ResponseEntity.ok(responseBean);
    }

    private static List<Question> getList(GenericResponseBean<AllQuestionOfQuizResponseDto> response) {
        return response.getData().getQuestions().stream().map(q -> {
            Question question = new Question();
            question.setId(q.getId());
            question.setQuestion(q.getQuestion());
            question.setOption1(q.getOption1());
            question.setOption2(q.getOption2());
            question.setOption3(q.getOption3());
            question.setOption4(q.getOption4());
            question.setDifficulty(q.getDifficulty());
            question.setQuizId(q.getQuizId());
            question.setCreatedAt(q.getCreatedAt());
            question.setUpdatedAt(q.getUpdatedAt());
            return question;
        }).collect(Collectors.toList());
    }
}
