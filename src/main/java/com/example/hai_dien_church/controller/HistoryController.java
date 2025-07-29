package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.HistoryRequest;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.dto.response.HistoryResponse;
import com.example.hai_dien_church.service.HistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HistoryController {

    HistoryService historyService;

    @GetMapping
    public ApiResponse<List<HistoryResponse>> getAllHistory(){
        return ApiResponse.<List<HistoryResponse>>builder()
                .result(historyService.getAll())
                .build();
    }

    @PostMapping
    public ApiResponse<HistoryResponse> createHistory(@RequestBody HistoryRequest historyRequest){
        return ApiResponse.<HistoryResponse>builder()
                .result(historyService.create(historyRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteHistory(@PathVariable("id") String id){
        historyService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete success")
                .build();
    }

}
