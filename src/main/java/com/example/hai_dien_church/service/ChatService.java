package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.ChatRequest;
import com.example.hai_dien_church.dto.response.ChatResponse;
import com.example.hai_dien_church.entity.Account;

import java.util.List;

public interface ChatService {

    List<ChatResponse> chatOfPost(String postId);
    ChatResponse createChat(String postId, ChatRequest chatRequest, Account account);

}
