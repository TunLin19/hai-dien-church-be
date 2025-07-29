package com.example.hai_dien_church.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActionResponse {

    String id;
    String title;
    String image;
    String description;
    String location;

}
