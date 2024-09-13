package com.vvquiz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String category;
    private String difficulty;
    private Integer quizId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}