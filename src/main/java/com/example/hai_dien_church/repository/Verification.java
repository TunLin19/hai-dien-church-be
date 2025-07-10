package com.example.hai_dien_church.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Verification extends JpaRepository<Verification,String> {
}
