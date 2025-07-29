package com.example.hai_dien_church.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatResponse {

    String message;
    LocalDateTime datetime;
    String nameAccount;

}
