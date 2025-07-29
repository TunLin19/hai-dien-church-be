package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.ChatRequest;
import com.example.hai_dien_church.dto.response.ChatResponse;
import com.example.hai_dien_church.entity.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    Chat toChat(ChatRequest chatRequest);
    ChatResponse toChatResponse(Chat chat);

}
