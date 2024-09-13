package com.vvscore.service;

import com.vvscore.dto.request.EvaluateUserQuizRequest;
import com.vvscore.dto.response.GenericResponseBean;
import org.springframework.http.ResponseEntity;

public interface UserScoreService {
    public ResponseEntity<GenericResponseBean<?>> evaluateUserQuiz(EvaluateUserQuizRequest request);
}
