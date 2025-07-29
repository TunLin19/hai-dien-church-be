package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History,String> {
}
