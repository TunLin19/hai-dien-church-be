package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.AccountRequest;
import com.example.hai_dien_church.dto.response.AccountResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.mapper.AccountMapper;
import com.example.hai_dien_church.repository.AccountRepository;
import com.example.hai_dien_church.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    AccountMapper accountMapper;

    @Override
    public AccountResponse informationAccount(Account account) {
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public void updateAccount(AccountRequest accountRequest, Account account) {
        accountMapper.updateAccount(account,accountRequest);
        accountRepository.save(account);
    }
}
