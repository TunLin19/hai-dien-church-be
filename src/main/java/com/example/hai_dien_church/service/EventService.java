package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.EventRequest;
import com.example.hai_dien_church.dto.response.AccountEventResponse;
import com.example.hai_dien_church.dto.response.EventResponse;
import com.example.hai_dien_church.entity.Account;

import java.util.List;

public interface EventService {

    EventResponse create(EventRequest eventRequest);
    List<EventResponse> getAll();
    List<AccountEventResponse> getAllAccountEvent(String eventId);
    void joinEvent(Account account, String eventId);
    void applyJoinEvent(String accountEventId);

}
