package com.vvsubject.service.impl;

import com.vvquiz.core.SubjectCreatedEventDto;
import com.vvsubject.dto.request.CreateSubjectRequestDto;
import com.vvsubject.dto.response.GenericResponseBean;
import com.vvsubject.dto.response.SubjectWithQuizResponseDto;
import com.vvsubject.entities.Quiz;
import com.vvsubject.entities.Subject;
import com.vvsubject.exception.SubjectAppException;
import com.vvsubject.producer.SubjectCreatedProducer;
import com.vvsubject.repository.SubjectRepository;
import com.vvsubject.service.SubjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final SubjectCreatedProducer subjectCreatedProducer;
    @Override
    public ResponseEntity<GenericResponseBean<?>> addSubject(CreateSubjectRequestDto createSubjectRequestDto, HttpServletRequest request) {
        try{
            Subject subject = modelMapper.map(createSubjectRequestDto, Subject.class);
            subjectRepository.save(subject);
            //produce email notification
            String email=request.getHeader("email");
            String name=request.getHeader("name");
            String role=request.getHeader("role");
            sendNotificationToTopic(new SubjectCreatedEventDto(),role,name,email,subject);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body(GenericResponseBean.builder().message("subject added successfully").status(true).build());
        }catch (Exception e){
            throw new SubjectAppException("Error while adding subject");
        }

    }

    private void sendNotificationToTopic(@NonNull SubjectCreatedEventDto subjectCreatedDto, String role, String name, String email, Subject subject) {
        subjectCreatedDto.setName(name);
        subjectCreatedDto.setEmail(email);
        subjectCreatedDto.setCode(subject.getCode());
        subjectCreatedDto.setRole(role);
        subjectCreatedProducer.sendSubjectCreatedEvent(subjectCreatedDto);
    }

    @Override
    @Transactional
    public ResponseEntity<GenericResponseBean<?>> deleteSubject(String subjectCode) {
            Subject subject = subjectRepository.findByCode(subjectCode).orElseThrow(()->new SubjectAppException("Subject not found with code "+subjectCode));
            subjectRepository.delete(subject);
            return ResponseEntity.status(HttpStatus.SC_OK).body(GenericResponseBean.builder().message("subject deleted successfully").status(true).build());
    }

    @Override
    @Transactional
    public ResponseEntity<GenericResponseBean<?>> updateSubject(String subjectCode, CreateSubjectRequestDto createSubjectRequestDto) {
        Subject subject = subjectRepository.findByCode(subjectCode).orElseThrow(() -> new SubjectAppException("Subject not found with code " + subjectCode));
        subject.setName(createSubjectRequestDto.getName());
        subjectRepository.save(subject);
        return ResponseEntity.status(HttpStatus.SC_OK).body(GenericResponseBean.builder().message("subject updated successfully").status(true).build());
    }

    @Override
    public ResponseEntity<GenericResponseBean<SubjectWithQuizResponseDto>> getSubjectWithoutQuiz(String subjectCode) {
        Subject subject = subjectRepository.findByCode(subjectCode).orElseThrow(() -> new SubjectAppException("Subject not found with code " + subjectCode));
        SubjectWithQuizResponseDto subjectWithQuizResponseDto = modelMapper.map(subject, SubjectWithQuizResponseDto.class);
        return ResponseEntity.status(HttpStatus.SC_OK).body(GenericResponseBean.<SubjectWithQuizResponseDto>builder().data(subjectWithQuizResponseDto).status(true).build());

    }

    @Override
    public ResponseEntity<GenericResponseBean<SubjectWithQuizResponseDto>> getSubjectWithQuiz(String subjectCode) {
        Subject subject = subjectRepository.findByCode(subjectCode).orElseThrow(() -> new SubjectAppException("Subject not found with code " + subjectCode));
        String url = "http://QUIZ_SERVICE/quiz/get/" + subjectCode;
        ResponseEntity<List<Quiz>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Quiz>>() {}
        );
        List<Quiz> quizzes = response.getBody();
        return ResponseEntity.status(HttpStatus.SC_OK).body(GenericResponseBean.<SubjectWithQuizResponseDto>builder().data(SubjectWithQuizResponseDto.builder().quizList(quizzes).code(subjectCode).name(subject.getName()).build()).status(true).build());
    }
}
