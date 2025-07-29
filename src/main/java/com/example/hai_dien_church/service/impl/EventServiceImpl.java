package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.EventRequest;
import com.example.hai_dien_church.dto.response.AccountEventResponse;
import com.example.hai_dien_church.dto.response.EventResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.entity.AccountEvent;
import com.example.hai_dien_church.entity.Event;
import com.example.hai_dien_church.enums.AccountEventStatus;
import com.example.hai_dien_church.exception.AppException;
import com.example.hai_dien_church.exception.ErrorCode;
import com.example.hai_dien_church.mapper.EventMapper;
import com.example.hai_dien_church.repository.AccountEventRepository;
import com.example.hai_dien_church.repository.EventRepository;
import com.example.hai_dien_church.service.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;
    AccountEventRepository accountEventRepository;
    EventMapper eventMapper;

    @Override
    public EventResponse create(EventRequest eventRequest) {
        Event event = eventMapper.toEvent(eventRequest);
        eventRepository.save(event);
        return eventMapper.toEventResponse(event);
    }

    @Override
    public List<EventResponse> getAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::toEventResponse).toList();
    }

    @Override
    public List<AccountEventResponse> getAllAccountEvent(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                ()-> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        List<AccountEvent> accountEvents = accountEventRepository.findByEvent(event);
        List<AccountEventResponse> accountEventResponses = new ArrayList<>();
        for (AccountEvent accountEvent: accountEvents) {
            AccountEventResponse accountEventResponse = new AccountEventResponse();
            accountEventResponse.setAccountEventId(accountEvent.getId());
            accountEventResponse.setFullName(accountEvent.getAccount().getFullName());
            accountEventResponse.setEmail(accountEvent.getAccount().getEmail());
            accountEventResponse.setPhone(accountEvent.getAccount().getPhone());
            accountEventResponse.setAddress(accountEvent.getAccount().getAddress());
            accountEventResponse.setAccountEventStatus(accountEvent.getEventStatus());
            accountEventResponses.add(accountEventResponse);
        }
        return accountEventResponses;
    }

    @Override
    public void joinEvent(Account account, String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                ()-> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        AccountEvent accountEvent = AccountEvent.builder()
                .account(account)
                .event(event)
                .eventStatus(AccountEventStatus.WAITING)
                .build();
        accountEventRepository.save(accountEvent);
    }

    @Override
    public void applyJoinEvent(String accountEventId) {
        AccountEvent accountEvent = accountEventRepository.findById(accountEventId).orElseThrow(
                ()-> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        accountEvent.setEventStatus(AccountEventStatus.ACTIVE);
        accountEventRepository.save(accountEvent);
    }

}
