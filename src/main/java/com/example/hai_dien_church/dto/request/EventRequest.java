package com.example.hai_dien_church.dto.request;

import com.example.hai_dien_church.enums.EventStatus;
import com.example.hai_dien_church.enums.EventType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EventRequest {

    String title;
    String image;
    String time;
    String date;
    EventType eventType;
    String institute;
    EventStatus eventStatus;
    String description;

}
