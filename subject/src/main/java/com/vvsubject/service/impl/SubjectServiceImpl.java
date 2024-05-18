package com.vvsubject.service.impl;

import com.vvsubject.dto.request.CreateSubjectRequestDto;
import com.vvsubject.dto.response.GenericResponseBean;
import com.vvsubject.entities.Subject;
import com.vvsubject.exception.SubjectAppException;
import com.vvsubject.repository.SubjectRepository;
import com.vvsubject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    @Override
    public ResponseEntity<GenericResponseBean<?>> addSubject(CreateSubjectRequestDto createSubjectRequestDto) {
        try{
            Subject subject = modelMapper.map(createSubjectRequestDto, Subject.class);
            subjectRepository.save(subject);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body(GenericResponseBean.builder().message("subject added successfully").status(true).build());
        }catch (Exception e){
            throw new SubjectAppException("Error while adding subject");
        }

    }
}
