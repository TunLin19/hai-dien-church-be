package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.response.*;
import com.example.hai_dien_church.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ViewController {

    // skip authentication
    BannerService bannerService;
    HistoryService historyService;
    EventService eventService;
    PostService postService;
    ActionService actionService;


    @GetMapping("/banner")
    public ApiResponse<List<BannerResponse>> getAllBanner(){
        return ApiResponse.<List<BannerResponse>>builder()
                .result(bannerService.getAllBanner())
                .build();
    }

    @GetMapping("/history")
    public ApiResponse<List<HistoryResponse>> getAllHistory(){
        return ApiResponse.<List<HistoryResponse>>builder()
                .result(historyService.getAll())
                .build();
    }

    @GetMapping("/event")
    public ApiResponse<List<EventResponse>> getAllEvent(){
        List<EventResponse> eventResponses = eventService.getAll();
        return ApiResponse.<List<EventResponse>>builder()
                .result(eventResponses)
                .build();
    }
    @GetMapping("/{eventId}/action")
    public ApiResponse<List<ActionResponse>> getAllAction(@PathVariable("eventId") String id){
        return ApiResponse.<List<ActionResponse>>builder()
                .result(actionService.getAllAction(id))
                .build();
    }

    @GetMapping("/post")
    public ApiResponse<List<PostResponse>> getAllPostConfirmed(){
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getPostsConfirmed())
                .build();
    }

}
