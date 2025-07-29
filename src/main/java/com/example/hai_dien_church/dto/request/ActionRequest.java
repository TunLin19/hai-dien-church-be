package com.example.hai_dien_church.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActionRequest {

    String title;
    String image;
    String description;
    String location;

}
