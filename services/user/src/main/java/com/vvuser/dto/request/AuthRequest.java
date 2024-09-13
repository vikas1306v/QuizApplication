package com.vvuser.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vvuser.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthRequest {
    private String phoneNo;
    private String admissionNo;
    private String rollNo;
    private String name;
    private String branch;
    private String course;
    private String section;
    private String email;
    private String password;
    private Role role;
}
