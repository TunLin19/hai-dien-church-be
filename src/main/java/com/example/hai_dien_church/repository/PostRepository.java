package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
}
