package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.LoginRequest;
import com.example.hai_dien_church.dto.request.SignupRequest;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.dto.response.AuthResponse;
import com.example.hai_dien_church.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/sent-otp-signup")
    public ApiResponse<Void> sentOtpSignup(@RequestBody SignupRequest signupRequest){
        authService.sentOtpSignup(signupRequest);
        return ApiResponse.<Void>builder().message("Sent otp success").build();
    }
    @PostMapping("/sent-otp-login")
    public ApiResponse<Void> sentOtpLogin(@RequestBody LoginRequest loginRequest){
        authService.sentOtpLogin(loginRequest);
        return ApiResponse.<Void>builder().message("Sent otp success").build();
    }

    @PostMapping("/signup")
    public ApiResponse<AuthResponse> signup(@RequestBody SignupRequest signupRequest){
        AuthResponse authResponse = authService.signup(signupRequest);
        return ApiResponse.<AuthResponse>builder()
                .result(authResponse)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.signing(loginRequest);
        return ApiResponse.<AuthResponse>builder()
                .result(authResponse)
                .build();
    }


}
