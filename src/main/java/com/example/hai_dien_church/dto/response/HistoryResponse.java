package com.example.hai_dien_church.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HistoryResponse {

    String id;
    String title;
    String url;
    Boolean type;
    String description;

}
