package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.HistoryRequest;
import com.example.hai_dien_church.dto.response.HistoryResponse;
import com.example.hai_dien_church.entity.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    History toHistory(HistoryRequest historyRequest);
    HistoryResponse toHistoryResponse(History history);

}
