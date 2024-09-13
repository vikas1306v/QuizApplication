package com.vvscore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer quizId;
    private Long score;
    //list of question id comma separated which user answered
    private String questionIds;
    // list of skipped question id comma separated
    private String skippedQuestionIds;
    private Integer correctAnswers;
    private Integer totalQuestions;
    private Integer totalSkipped;
    private Boolean isPassed;
    private LocalDateTime startedAt;
    private LocalDateTime finishAt;
}
