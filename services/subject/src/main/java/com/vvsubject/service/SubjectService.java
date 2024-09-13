package com.vvsubject.service;

import com.vvsubject.dto.request.CreateSubjectRequestDto;
import com.vvsubject.dto.response.GenericResponseBean;
import com.vvsubject.dto.response.SubjectWithQuizResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface SubjectService {
    public ResponseEntity<GenericResponseBean<?>> addSubject(CreateSubjectRequestDto createSubjectRequestDto, HttpServletRequest request);

    ResponseEntity<GenericResponseBean<?>> deleteSubject(String subjectCode);

    ResponseEntity<GenericResponseBean<?>> updateSubject(String subjectCode, CreateSubjectRequestDto createSubjectRequestDto);

    ResponseEntity<GenericResponseBean<SubjectWithQuizResponseDto>> getSubjectWithoutQuiz(String subjectCode);

    ResponseEntity<GenericResponseBean<SubjectWithQuizResponseDto>> getSubjectWithQuiz(String subjectCode);
}
