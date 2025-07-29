package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.ActionRequest;
import com.example.hai_dien_church.dto.response.ActionResponse;
import com.example.hai_dien_church.entity.Action;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActionMapper {

    Action toAction(ActionRequest actionRequest);
    ActionResponse toActionResponse(Action action);
    void updateAction(@MappingTarget Action action, ActionRequest actionRequest);

}
