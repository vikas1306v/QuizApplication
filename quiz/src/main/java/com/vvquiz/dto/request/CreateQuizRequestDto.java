package com.vvquiz.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vvquiz.utils.QuizType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateQuizRequestDto {
    private Integer id;
    private String quizCode;
    private String title;
    private String description;
    private QuizType type;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate quizDate;
    private Integer totalQuestions;
    private Integer totalMarks;
    private Integer passingMarks;
    private Integer duration;
    private LocalTime loginTime;
    private boolean active;
    private Integer createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private Set<String> tags;
    private String instructions;
    private Integer maxAttempts;
    private boolean isPublic;

}
