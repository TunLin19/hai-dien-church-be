package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.HistoryRequest;
import com.example.hai_dien_church.dto.response.HistoryResponse;
import com.example.hai_dien_church.dto.response.HistoryResponse;
import com.example.hai_dien_church.entity.History;
import com.example.hai_dien_church.mapper.HistoryMapper;
import com.example.hai_dien_church.repository.HistoryRepository;
import com.example.hai_dien_church.service.HistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HistoryServiceImpl implements HistoryService {

    HistoryRepository historyRepository;
    HistoryMapper historyMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public HistoryResponse create(HistoryRequest historyRequest) {
        History history = historyMapper.toHistory(historyRequest);
        if (historyRequest.getUrl().toLowerCase().endsWith(".mp4")){
            history.setType(false);
        }else
            history.setType(true);
        historyRepository.save(history);
        return historyMapper.toHistoryResponse(history);
    }

    @Override
    public List<HistoryResponse> getAll() {
        List<History> list = historyRepository.findAll();
        List<HistoryResponse> historyResponses = list
                .stream()
                .map(historyMapper::toHistoryResponse)
                .toList();
        return historyResponses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void delete(String id) {

        historyRepository.deleteById(id);

    }
}
