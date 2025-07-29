package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.AccountRequest;
import com.example.hai_dien_church.dto.response.AccountResponse;
import com.example.hai_dien_church.entity.Account;

public interface AccountService {

    AccountResponse informationAccount(Account account);
    void updateAccount(AccountRequest accountRequest, Account account);

}
