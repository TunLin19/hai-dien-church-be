package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,String> {

    @Query("SELECT c FROM Chat c WHERE c.post.id = :postId")
    List<Chat> findByPostId(@Param("postId") String postId);

}
