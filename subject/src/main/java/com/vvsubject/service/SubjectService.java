package com.vvsubject.service;

import com.vvsubject.dto.request.CreateSubjectRequestDto;
import com.vvsubject.dto.response.GenericResponseBean;
import org.springframework.http.ResponseEntity;

public interface SubjectService {
    public ResponseEntity<GenericResponseBean<?>> addSubject(CreateSubjectRequestDto createSubjectRequestDto);
}
