package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.EventRequest;
import com.example.hai_dien_church.dto.response.EventResponse;
import com.example.hai_dien_church.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEvent(EventRequest eventRequest);

    EventResponse toEventResponse(Event event);

}
