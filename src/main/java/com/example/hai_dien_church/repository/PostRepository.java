package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.entity.Post;
import com.example.hai_dien_church.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findByPostStatus(PostStatus postStatus);
    Post findPostByIdAndAccount(String id, Account account);

}
