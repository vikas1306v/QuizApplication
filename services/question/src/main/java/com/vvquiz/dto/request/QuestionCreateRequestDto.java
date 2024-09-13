package com.vvquiz.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionCreateRequestDto {

    @NotBlank(message = "Question text is mandatory")
    private String question;

    @NotBlank(message = "Option 1 is mandatory")
    private String option1;

    @NotBlank(message = "Option 2 is mandatory")
    private String option2;

    @NotBlank(message = "Option 3 is mandatory")
    private String option3;

    @NotBlank(message = "Option 4 is mandatory")
    private String option4;

    @NotBlank(message = "Difficulty level is mandatory")
    private String difficulty;

    @NotNull(message = "Quiz ID is mandatory")
    private Integer quizId;
}
