package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.EventRequest;
import com.example.hai_dien_church.dto.response.AccountEventResponse;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.dto.response.EventResponse;
import com.example.hai_dien_church.entity.Account;
import com.example.hai_dien_church.service.AuthService;
import com.example.hai_dien_church.service.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {

    EventService eventService;
    AuthService authService;

    @PostMapping
    public ApiResponse<EventResponse> createEvent(@RequestBody EventRequest eventRequest){
        EventResponse eventResponse = eventService.create(eventRequest);
        return ApiResponse.<EventResponse>builder()
                .message("create success")
                .result(eventResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<EventResponse>> getAllEvent(){
        List<EventResponse> eventResponses = eventService.getAll();
        return ApiResponse.<List<EventResponse>>builder()
                .result(eventResponses)
                .build();
    }

    @GetMapping("/{eventId}")
    public ApiResponse<List<AccountEventResponse>> getAccountEvent(@PathVariable("eventId") String id){
        List<AccountEventResponse> accountEventResponses = eventService.getAllAccountEvent(id);
        return ApiResponse.<List<AccountEventResponse>>builder()
                .result(accountEventResponses)
                .build();
    }

    @PostMapping("/{eventId}")
    public ApiResponse<Void> joinEvent(@PathVariable("eventId") String id){
        Account account = authService.authentication();
        eventService.joinEvent(account,id);
        return ApiResponse.<Void>builder()
                .message("Sent success")
                .build();
    }

    @PostMapping("/{accountEventId}")
    public ApiResponse<Void> applyJoinEvent(@PathVariable("accountEventId") String id){
        eventService.applyJoinEvent(id);
        return ApiResponse.<Void>builder()
                .message("Sent success")
                .build();
    }

}
