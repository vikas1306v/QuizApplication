package com.vvuser.service;

import com.vvuser.dto.request.AuthRequest;
import com.vvuser.dto.response.AuthResponse;
import com.vvuser.dto.response.GenericResponseBean;
import com.vvuser.entities.User;
import com.vvuser.exception.InvalidCredentialsException;
import com.vvuser.repository.UserRepository;
import com.vvuser.utils.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<GenericResponseBean<AuthResponse>> createAndLoginUser(AuthRequest authRequest) {
        try{
            //login and create token
            User user = userRepository.findByAdmissionNo(authRequest.getAdmissionNo()).orElseThrow(() -> new UsernameNotFoundException("User Not found "));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getAdmissionNo(),
                            authRequest.getPassword()));
            String token=generateTokenOfCreatedUser(authRequest);
            return ResponseEntity.status(HttpStatus.OK).body(
                    GenericResponseBean.<AuthResponse>builder().data(
                            AuthResponse.builder().token(token).
                                    admissionNo(authRequest.getAdmissionNo()).
                                    branch(authRequest.getBranch()).
                                    role(authRequest.getRole().name()).
                                    userId(authRequest.getAdmissionNo()).
                                    build()).build());
        } catch (UsernameNotFoundException ex) {
            //create and generate token
            User user=new User();
            createUser(authRequest,user);
            saveUser(user);
            String token=generateTokenOfCreatedUser(authRequest);
            return ResponseEntity.status(HttpStatus.OK).body(
                    GenericResponseBean.<AuthResponse>builder().data(
                            AuthResponse.builder().token(token).
                                    admissionNo(user.getAdmissionNo()).
                                    branch(user.getBranch()).
                                    role(user.getRole().name()).
                                    userId(user.getUserId()).
                                    build()).build());}
        catch (AuthenticationException ex){
            throw new InvalidCredentialsException("Invalid username/password supplied");
        }
    }
    private String generateTokenOfCreatedUser(AuthRequest authRequest) {
        Map<String,Object> extraClaims=new HashMap<>();
        User user = createUser(authRequest, new User());
        extraClaims.put("user",user);
        return jwtService.generateToken(extraClaims,authRequest.getAdmissionNo());
    }


    private User createUser(AuthRequest authRequest, User user) {
        String userId= UUID.randomUUID().toString();
        user.setAdmissionNo(authRequest.getAdmissionNo());
        user.setBranch(authRequest.getBranch());
        user.setName(authRequest.getName());
        user.setCourse(authRequest.getCourse());
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setPhoneNo(authRequest.getPhoneNo());
        switch (authRequest.getRole()){
            case STUDENT:
                user.setRole(Role.STUDENT);
                break;
            case FACULTY:
                user.setRole(Role.FACULTY);
                break;
            case ADMIN:
                user.setRole(Role.ADMIN);
                break;
        }
        user.setSection(authRequest.getSection());
        user.setRollNo(authRequest.getRollNo());
        user.setUserId(userId);
        return user;
    }
    @Transactional
     public void saveUser(User user){
        userRepository.save(user);
     }
}
