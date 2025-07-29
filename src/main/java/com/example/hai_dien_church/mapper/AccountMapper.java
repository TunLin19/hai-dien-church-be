package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.AccountRequest;
import com.example.hai_dien_church.dto.response.AccountResponse;
import com.example.hai_dien_church.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountRequest accountRequest);
    AccountResponse toAccountResponse(Account account);
    void updateAccount(@MappingTarget Account account, AccountRequest accountRequest);

}
