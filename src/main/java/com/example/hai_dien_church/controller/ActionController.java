package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.ActionRequest;
import com.example.hai_dien_church.dto.response.ActionResponse;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.service.ActionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api/action")
public class ActionController {

    ActionService actionService;

    @GetMapping
    public ApiResponse<List<ActionResponse>> getAll(@RequestParam("eventId") String id){
        return ApiResponse.<List<ActionResponse>>builder()
                .result(actionService.getAllAction(id))
                .build();
    }

    @PostMapping
    public ApiResponse<ActionResponse> create(@RequestParam("eventId") String id,
                                              @RequestBody ActionRequest actionRequest){
        return ApiResponse.<ActionResponse>builder()
                .message("create success")
                .result(actionService.createAction(id,actionRequest))
                .build();
    }

    @PatchMapping
    public ApiResponse<Void> update(@RequestParam("actionId") String id,
                                    @RequestBody ActionRequest actionRequest){
        actionService.updateAction(id,actionRequest);
        return ApiResponse.<Void>builder()
                .message("update success")
                .build();
    }

}
