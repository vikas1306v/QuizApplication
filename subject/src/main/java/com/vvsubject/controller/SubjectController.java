package com.vvsubject.controller;

import com.vvsubject.dto.request.CreateSubjectRequestDto;
import com.vvsubject.dto.response.GenericResponseBean;
import com.vvsubject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping(value = "/addSubject",produces = "application/json",consumes = "application/json")
    public ResponseEntity<GenericResponseBean<?>> addSubject(@RequestBody CreateSubjectRequestDto createSubjectRequestDto) {
        return subjectService.addSubject(createSubjectRequestDto);
    }
}
