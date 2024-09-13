package com.vvscore.dto.request;

import java.util.List;
import java.util.Map;

public class EvaluateUserQuizRequest {
    private String userId;
    private String quizId;
    private List<Map<Integer, String>> answers;
}
