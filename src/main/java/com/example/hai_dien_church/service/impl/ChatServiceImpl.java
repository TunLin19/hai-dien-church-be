package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.ChatRequest;
import com.example.hai_dien_church.dto.response.ChatResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.entity.Chat;
import com.example.hai_dien_church.entity.Post;
import com.example.hai_dien_church.exception.AppException;
import com.example.hai_dien_church.exception.ErrorCode;
import com.example.hai_dien_church.mapper.ChatMapper;
import com.example.hai_dien_church.repository.ChatRepository;
import com.example.hai_dien_church.repository.PostRepository;
import com.example.hai_dien_church.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;
    ChatMapper chatMapper;
    PostRepository postRepository;

    @Override
    public List<ChatResponse> chatOfPost(String postId) {

        List<Chat> chatList = chatRepository.findByPostId(postId);
        return chatList.stream().map(chatMapper::toChatResponse).toList();
    }

    @Override
    public ChatResponse createChat(String postId, ChatRequest chatRequest, Account account) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        Chat chat = chatMapper.toChat(chatRequest);
        chat.setPost(post);
        chat.setAccount(account);
        chatRepository.save(chat);
        return chatMapper.toChatResponse(chat);
    }
}
