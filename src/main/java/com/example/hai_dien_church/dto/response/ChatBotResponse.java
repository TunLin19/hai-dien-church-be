package com.example.hai_dien_church.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatBotResponse {

    List<HistoryResponse> historyResponses;
    List<EventResponse> eventResponses;
    List<ActionResponse> actionResponses;

}
