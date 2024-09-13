package com.vvscore.service.impl;

import com.vvscore.dto.request.EvaluateUserQuizRequest;
import com.vvscore.dto.response.GenericResponseBean;
import com.vvscore.service.UserScoreService;
import org.springframework.http.ResponseEntity;

public class UserScoreServiceImpl implements UserScoreService {
    @Override
    public ResponseEntity<GenericResponseBean<?>> evaluateUserQuiz(EvaluateUserQuizRequest request) {
        return null;
    }
}
