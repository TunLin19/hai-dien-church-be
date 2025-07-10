package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner,String> {
}
