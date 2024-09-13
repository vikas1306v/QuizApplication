package com.vvquiz.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vvquiz.utils.QuizType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateQuizRequestDto {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Quiz type is mandatory")
    private QuizType type;

    @NotNull(message = "Start time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "End time is mandatory")
    private LocalTime endTime;

    @NotNull(message = "Quiz date is mandatory")
    private LocalDate quizDate;

    @NotNull(message = "Total questions is mandatory")
    @Min(value = 1, message = "Total questions must be at least 1")
    private Integer totalQuestions;

    @NotNull(message = "Total marks is mandatory")
    @Min(value = 1, message = "Total marks must be at least 1")
    private Integer totalMarks;

    @NotNull(message = "Passing marks is mandatory")
    @Min(value = 1, message = "Passing marks must be at least 1")
    private Integer passingMarks;

    @NotNull(message = "Duration is mandatory")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    @NotNull(message = "Login time is mandatory")
    private LocalTime loginTime;

    private boolean active;

    @NotNull(message = "Created by user ID is mandatory")
    private Integer createdByUserId;

    @NotNull(message = "Created at timestamp is mandatory")
    private LocalDateTime createdAt;

    @NotNull(message = "Updated at timestamp is mandatory")
    private LocalDateTime updatedAt;

    @NotBlank(message = "Category is mandatory")
    private String category;

    private String instructions;

    @NotNull(message = "Max attempts is mandatory")
    @Min(value = 1, message = "Max attempts must be at least 1")
    private Integer maxAttempts;

    private boolean isPublic;
    @NotNull(message = "Subject ID is mandatory")
    private Integer subjectId;
}
