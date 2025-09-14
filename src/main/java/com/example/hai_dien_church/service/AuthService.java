package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.LoginRequest;
import com.example.hai_dien_church.dto.request.SignupRequest;
import com.example.hai_dien_church.dto.response.AuthResponse;
import com.example.hai_dien_church.dto.response.IntrospectResponse;
import com.example.hai_dien_church.entity.Account;

public interface AuthService {

    void sentOtpLogin(LoginRequest loginRequest);
    void sentOtpSignup(SignupRequest signupRequest);
    String reflectOtp(String email);
    Account authentication();
    AuthResponse signup(SignupRequest signupRequest);
    AuthResponse signing(LoginRequest loginRequest);
    IntrospectResponse introspect(String token);
    AuthResponse outboundAuthentication(String code);
}
