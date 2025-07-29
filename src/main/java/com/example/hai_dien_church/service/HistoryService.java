package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.HistoryRequest;
import com.example.hai_dien_church.dto.response.HistoryResponse;

import java.util.List;

public interface HistoryService {
    HistoryResponse create(HistoryRequest historyRequest);
    List<HistoryResponse> getAll();
    void delete(String id);
}
