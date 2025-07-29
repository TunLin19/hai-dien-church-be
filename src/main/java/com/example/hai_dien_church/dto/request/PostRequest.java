package com.example.hai_dien_church.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {

    String title;
    String image;
    LocalDateTime datetime;
    String description;



}
