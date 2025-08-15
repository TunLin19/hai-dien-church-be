package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.response.ActionResponse;
import com.example.hai_dien_church.dto.response.ChatBotResponse;
import com.example.hai_dien_church.dto.response.EventResponse;
import com.example.hai_dien_church.dto.response.HistoryResponse;
import com.example.hai_dien_church.entity.Action;
import com.example.hai_dien_church.entity.Event;
import com.example.hai_dien_church.entity.History;
import com.example.hai_dien_church.mapper.ActionMapper;
import com.example.hai_dien_church.mapper.EventMapper;
import com.example.hai_dien_church.mapper.HistoryMapper;
import com.example.hai_dien_church.repository.ActionRepository;
import com.example.hai_dien_church.repository.EventRepository;
import com.example.hai_dien_church.repository.HistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatBotService {

    HistoryRepository historyRepository;
    HistoryMapper historyMapper;
    EventRepository eventRepository;
    EventMapper eventMapper;
    ActionRepository actionRepository;
    ActionMapper actionMapper;

    public ChatBotResponse getAll(){

        List<History> histories = historyRepository.findAll();
        List<HistoryResponse> historyResponses = histories
                .stream()
                .map(historyMapper::toHistoryResponse)
                .toList();

        List<Event> events = eventRepository.findAll();
        List<EventResponse> eventResponses = events
                .stream()
                .map(eventMapper::toEventResponse)
                .toList();

        List<Action> actions = actionRepository.findAll();
        List<ActionResponse> actionResponses = actions
                .stream()
                .map(actionMapper::toActionResponse)
                .toList();

        return ChatBotResponse.builder()
                .historyResponses(historyResponses)
                .eventResponses(eventResponses)
                .actionResponses(actionResponses)
                .build();

    }

}
