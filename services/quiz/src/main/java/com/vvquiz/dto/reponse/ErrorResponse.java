package com.vvquiz.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private List<String> messages;
}
