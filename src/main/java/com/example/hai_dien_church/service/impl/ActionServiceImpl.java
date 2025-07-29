package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.ActionRequest;
import com.example.hai_dien_church.dto.response.ActionResponse;
import com.example.hai_dien_church.entity.Action;
import com.example.hai_dien_church.entity.Event;
import com.example.hai_dien_church.exception.AppException;
import com.example.hai_dien_church.exception.ErrorCode;
import com.example.hai_dien_church.mapper.ActionMapper;
import com.example.hai_dien_church.repository.ActionRepository;
import com.example.hai_dien_church.repository.EventRepository;
import com.example.hai_dien_church.service.ActionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ActionServiceImpl implements ActionService {

    ActionRepository actionRepository;
    ActionMapper actionMapper;
    EventRepository eventRepository;

    @Override
    public List<ActionResponse> getAllAction(String eventId) {
        List<Action> actions = actionRepository.findByEventId(eventId);
        return actions.stream().map(actionMapper::toActionResponse).toList();
    }

    @Override
    public ActionResponse createAction(String eventId, ActionRequest actionRequest) {
        Action action = actionMapper.toAction(actionRequest);
        Event event = eventRepository.findById(eventId).orElseThrow(
                ()-> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        action.setEvent(event);
        Action actionNew = actionRepository.save(action);
        return actionMapper.toActionResponse(actionNew);
    }

    @Override
    public void updateAction(String actionId, ActionRequest actionRequest) {
        Action action = actionRepository.findById(actionId).orElseThrow(
                ()-> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        actionMapper.updateAction(action,actionRequest);
        actionRepository.save(action);
    }
}
