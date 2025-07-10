package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,String> {
}
