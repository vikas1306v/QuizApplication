package com.vvuser.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthResponse {
    private String userId;
    private String token;
    private String role;
    private String admissionNo;
    private String branch;
}
