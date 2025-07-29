package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.AccountRequest;
import com.example.hai_dien_church.dto.response.AccountResponse;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.service.AccountService;
import com.example.hai_dien_church.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api")
public class AccountController {

    AccountService accountService;
    AuthService authService;

    @GetMapping("/info")
    public ApiResponse<AccountResponse> information(){

        Account account = authService.authentication();
        AccountResponse accountResponse = accountService.informationAccount(account);
        return ApiResponse.<AccountResponse>builder().result(accountResponse).build();

    }

    @PatchMapping("/change")
    public ApiResponse<Void> changeAccount(@RequestBody AccountRequest accountRequest){
        Account account = authService.authentication();
        accountService.updateAccount(accountRequest,account);
        return ApiResponse.<Void>builder().message("update success").build();
    }


}
