package com.example.hai_dien_church.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HistoryRequest {

    String title;
    String url;
    String description;

}
