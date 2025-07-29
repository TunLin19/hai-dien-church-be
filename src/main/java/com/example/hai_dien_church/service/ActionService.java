package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.ActionRequest;
import com.example.hai_dien_church.dto.response.ActionResponse;

import java.util.List;

public interface ActionService {

    List<ActionResponse> getAllAction(String eventId);
    ActionResponse createAction(String eventId, ActionRequest actionRequest);
    void updateAction(String actionId, ActionRequest actionRequest);

}
