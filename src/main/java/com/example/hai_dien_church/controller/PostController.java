package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.PostRequest;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.dto.response.PostResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.service.AuthService;
import com.example.hai_dien_church.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;
    AuthService authService;

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPost(){
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getAllPost())
                .build();
    }

    @PostMapping
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest postRequest){
        Account account = authService.authentication();
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(postRequest,account))
                .build();
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable("postId") String postId){
        Account account = authService.authentication();
        postService.deletePost(postId, account);
        return ApiResponse.<Void>builder()
                .message("delete success")
                .build();
    }

}
