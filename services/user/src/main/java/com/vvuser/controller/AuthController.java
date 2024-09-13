package com.vvuser.controller;

import com.vvuser.dto.request.AuthRequest;
import com.vvuser.dto.response.AuthResponse;
import com.vvuser.dto.response.GenericResponseBean;
import com.vvuser.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping
    public ResponseEntity<GenericResponseBean<AuthResponse>> createAndLoginUser(@RequestBody AuthRequest authRequest) {
        return authService.createAndLoginUser(authRequest);
    }
}
