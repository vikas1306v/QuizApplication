package com.vvsubject.controller;

import com.vvsubject.dto.request.CreateSubjectRequestDto;
import com.vvsubject.dto.response.GenericResponseBean;
import com.vvsubject.dto.response.SubjectWithQuizResponseDto;
import com.vvsubject.service.SubjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping(value = "/add", produces = "application/json",consumes = "application/json")
    public ResponseEntity<GenericResponseBean<?>> addSubject(@RequestBody CreateSubjectRequestDto createSubjectRequestDto, HttpServletRequest request) {
        return subjectService.addSubject(createSubjectRequestDto,request);
    }
    @DeleteMapping("/{subject_code}")
    public  ResponseEntity<GenericResponseBean<?>> deleteSubject(@PathVariable(value = "subject_code",required = true) String subject_code){
        return subjectService.deleteSubject(subject_code);
    }
    @PutMapping("/{subject_code}")
    public ResponseEntity<GenericResponseBean<?>> updateSubject(@PathVariable(value = "subject_code",required = true) String subject_code,
                                                                @RequestBody CreateSubjectRequestDto createSubjectRequestDto){
        return subjectService.updateSubject(subject_code,createSubjectRequestDto);
    }
    @GetMapping("/{subject_code}")
    public ResponseEntity<GenericResponseBean<SubjectWithQuizResponseDto>> getSubjectWithoutQuiz(@PathVariable(value = "subject_code") String subject_code){
        return subjectService.getSubjectWithoutQuiz(subject_code);
    }
    @GetMapping("/get/{subject_code}")
    public ResponseEntity<GenericResponseBean<SubjectWithQuizResponseDto>> getSubjectWithQuiz(@PathVariable(value = "subject_code") String subject_code){
        return subjectService.getSubjectWithQuiz(subject_code);
    }

}
