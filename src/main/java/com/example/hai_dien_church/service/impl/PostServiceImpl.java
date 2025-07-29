package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.PostRequest;
import com.example.hai_dien_church.dto.response.PostResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.entity.Chat;
import com.example.hai_dien_church.entity.Post;
import com.example.hai_dien_church.enums.PostStatus;
import com.example.hai_dien_church.mapper.PostMapper;
import com.example.hai_dien_church.repository.ChatRepository;
import com.example.hai_dien_church.repository.PostRepository;
import com.example.hai_dien_church.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostMapper postMapper;
    ChatRepository chatRepository;

    @Override
    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(postMapper::toPostResponse).toList();
    }

    @Override
    public List<PostResponse> getPostsConfirmed() {
        List<Post> posts = postRepository.findByPostStatus(PostStatus.CONFIRMED);
        return posts.stream().map(postMapper::toPostResponse).toList();
    }

    @Override
    public PostResponse createPost(PostRequest postRequest, Account account) {
        Post post = postMapper.toPost(postRequest);
        post.setPostStatus(PostStatus.NON_CONFIRM);
        post.setAccount(account);
        Post postNew = postRepository.save(post);
        PostResponse postResponse = postMapper.toPostResponse(postNew);
        postResponse.setNameAccount(account.getFullName());
        return postResponse;
    }

    @Override
    public void deletePost(String postId, Account account) {

        Post post = postRepository.findPostByIdAndAccount(postId,account);
        List<Chat> listChat = chatRepository.findByPostId(postId);
        if (listChat.size() > 0){
            chatRepository.deleteAll(listChat);
        }
        postRepository.delete(post);

    }
}
