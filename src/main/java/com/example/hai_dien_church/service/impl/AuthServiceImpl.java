package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.ExchangeRequest;
import com.example.hai_dien_church.dto.request.LoginRequest;
import com.example.hai_dien_church.dto.request.SignupRequest;
import com.example.hai_dien_church.dto.response.AuthResponse;
import com.example.hai_dien_church.dto.response.IntrospectResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.entity.Role;
import com.example.hai_dien_church.entity.Verification;
import com.example.hai_dien_church.exception.AppException;
import com.example.hai_dien_church.exception.ErrorCode;
import com.example.hai_dien_church.repository.AccountRepository;
import com.example.hai_dien_church.repository.http_client.OutboundIdentityClient;
import com.example.hai_dien_church.repository.RoleRepository;
import com.example.hai_dien_church.repository.VerificationRepository;
import com.example.hai_dien_church.repository.http_client.OutboundUserClient;
import com.example.hai_dien_church.service.AuthService;
import com.example.hai_dien_church.utils.MailUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthServiceImpl implements AuthService {

    AccountRepository accountRepository;
    VerificationRepository verificationRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    MailUtil mailUtil;
    OutboundIdentityClient outboundIdentityClient;
    OutboundUserClient outboundUserClient;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    protected final String CLIENT_ID="40476816046-768146e5dvv4p6a1d4er3kpl6gb4ekuc.apps.googleusercontent.com";

    @NonFinal
    protected final String CLIENT_SECRET="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";

    @NonFinal
    protected final String REDIRECT_URI="http://localhost:5173/authenticate";

    @NonFinal
    protected final String GRANT_TYPE="authorization_code";

    @Override
    public void sentOtpLogin(LoginRequest loginRequest) {

        Account account = accountRepository.findByEmail(loginRequest.getEmail());
        if (account != null){
            if (passwordEncoder.matches(loginRequest.getPassword(),account.getPassword())){
                String otp = reflectOtp(loginRequest.getEmail());
                mailUtil.sendEmailFormat(loginRequest.getEmail(),"Mã xác thực OTP đăng nhập",header(),otpContent(otp),footer());
            }else {
                throw new AppException(ErrorCode.INVALID_VALUE);
            }
        }else {
            throw new AppException(ErrorCode.ACCOUNT_NOT_EXIT);
        }

    }

    @Override
    public void sentOtpSignup(SignupRequest signupRequest) {

        Account account = accountRepository.findByEmail(signupRequest.getEmail());
        if (account == null){
                String otp = reflectOtp(signupRequest.getEmail());
                mailUtil.sendEmailFormat(signupRequest.getEmail(),"Mã xác thực OTP đăng ký",header(),otpContent(otp),footer());
        }else {
            throw new AppException(ErrorCode.ACCOUNT_EXIT);
        }

    }

    private String header() {
        return "<tr><td style=\"padding: 24px; text-align: center; background-color: #ffedd5; border-top-left-radius: 16px; border-top-right-radius: 16px;\">" +
                "    <h2 style=\"margin: 0; color: #d97706;\">Xác thực tài khoản</h2>" +
                "</td></tr>";
    }

    private String otpContent(String otpCode) {
        return "<tr><td style=\"padding: 32px; text-align: center;\">" +
                "    <p style=\"font-size: 16px; color: #4b5563;\">Mã OTP của bạn là:</p>" +
                "    <p style=\"font-size: 32px; font-weight: bold; color: #ef4444; margin: 16px 0;\">" + otpCode + "</p>" +
                "    <p style=\"font-size: 14px; color: #6b7280;\">Mã này có hiệu lực trong 15 phút. Vui lòng không chia sẻ mã này với bất kỳ ai.</p>" +
                "</td></tr>";
    }

    private String footer() {
        return "<tr><td style=\"padding: 24px; text-align: center; background-color: #f3f4f6; border-bottom-left-radius: 16px; border-bottom-right-radius: 16px;\">" +
                "    <p style=\"font-size: 12px; color: #9ca3af;\">© 2025 Your Company. Mọi quyền được bảo lưu.</p>" +
                "</td></tr>";
    }

    @Override
    public String reflectOtp(String email) {

        Verification verification = verificationRepository.findByEmail(email);
        if (verification != null){
            verificationRepository.delete(verification);
        }
        String otp = generateOtp();
        Verification verificationNew = Verification.builder()
                .email(email)
                .otp(otp)
                .expireTime(LocalDateTime.now().plusMinutes(15))
                .build();
        verificationRepository.save(verificationNew);
        return otp;
    }

    private static String generateOtp(){
        int otpLength =6;

        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i=0; i<otpLength ;i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public Account authentication() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        Account account = accountRepository.findByEmail(email);
        if (account == null){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return account;
    }

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {

        if (submitOtp(signupRequest.getEmail(), signupRequest.getOtp())) {
            Role role = roleRepository.findByName("USER");
            Account account = Account.builder()
                    .email(signupRequest.getEmail())
                    .fullName(signupRequest.getFullName())
                    .password(passwordEncoder.encode(signupRequest.getPassword()))
                    .role(role)
                    .build();
            accountRepository.save(account);
            String token = generateToken(account);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }


    }

    @Override
    public AuthResponse signing(LoginRequest loginRequest) {
        if (submitOtp(loginRequest.getEmail(), loginRequest.getOtp())) {
            Account account = accountRepository.findByEmail(loginRequest.getEmail());
            String token = generateToken(account);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    @Override
    public IntrospectResponse introspect(String token) {
        boolean isValid = true;

        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            var verified = signedJWT.verify(verifier);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        } catch (AppException | JOSEException | ParseException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public AuthResponse outboundAuthentication(String code) {
        var response = outboundIdentityClient.exchangeToken(ExchangeRequest.builder()
                .code(code)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .redirectUri(REDIRECT_URI)
                .grantType(GRANT_TYPE)
                .build());
        var userInfo = outboundUserClient.getUserInfo("json",response.getAccessToken());
        log.info("user {}",userInfo);
        Role role = roleRepository.findByName("USER");
        var account = accountRepository.findByEmail(userInfo.getEmail());
        if (account == null){
            account = accountRepository.save(account.builder()
                    .email(userInfo.getEmail())
                    .fullName(userInfo.getGivenName())
                    .role(role)
                    .build());
        }

        String token = generateToken(account);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    private boolean submitOtp(String email, String otp){
        Verification verification = verificationRepository.findByEmail(email);
        if (verification != null){
            if (verification.getExpireTime().isBefore(LocalDateTime.now())){
                throw new AppException(ErrorCode.EXPIRE_VALUE);
            }else if(!verification.getOtp().equals(otp)){
                throw new AppException(ErrorCode.INVALID_VALUE);
            }
            return true;
        }
        return false;
    }

    private String generateToken(Account account){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(account.getEmail())
                .issuer("nhathohaidien.com")
                .issueTime(new Date())
                .claim("scope","ROLE_"+account.getRole().getName())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();

        } catch (JOSEException e) {
            log.error("Can't create token: {}", e.getMessage());
            throw new RuntimeException("Token generation failed");
        }
    }
}
