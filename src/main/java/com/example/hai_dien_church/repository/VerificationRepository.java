package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification,String> {

    Verification findByEmail(String email);

}
