package com.vvquiz.entities;

import com.vvquiz.utils.QuizType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String instructions;
    private Integer maxAttempts;
    private boolean isPublic;
    @Transient
    List<Question> questions;
    private Integer subjectId;
}
