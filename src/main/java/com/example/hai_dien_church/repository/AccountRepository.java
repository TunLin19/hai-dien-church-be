package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

    Account findByEmail(String email);

}
