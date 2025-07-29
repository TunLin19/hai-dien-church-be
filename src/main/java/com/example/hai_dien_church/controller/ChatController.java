package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.ChatRequest;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.dto.response.ChatResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.service.AuthService;
import com.example.hai_dien_church.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {

    ChatService chatService;
    AuthService authService;

    @PostMapping
    public ApiResponse<ChatResponse> createChat(@RequestParam("postId") String postId,
                                                @RequestBody ChatRequest chatRequest){
        Account account = authService.authentication();
        return ApiResponse.<ChatResponse>builder()
                .result(chatService.createChat(postId,chatRequest,account))
                .build();
    }

}
