package com.vvquiz.utils;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public enum QuizType {
    MCQ,
    TRUE_FALSE,
    FILL_IN_THE_BLANK,
    MATCH_THE_FOLLOWING,
    SHORT_ANSWER,
    LONG_ANSWER
}
