package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.PostRequest;
import com.example.hai_dien_church.dto.response.PostResponse;
import com.example.hai_dien_church.entity.Account;

import java.util.List;

public interface PostService {

    List<PostResponse> getAllPost();
    List<PostResponse> getPostsConfirmed();
    PostResponse createPost(PostRequest postRequest, Account account);
    void deletePost(String postId, Account account);

}
