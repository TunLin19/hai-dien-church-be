package com.example.hai_dien_church.dto.response;

import com.example.hai_dien_church.enums.PostStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {

    String id;
    String title;
    String image;
    LocalDateTime datetime;
    String description;
    String nameAccount;
    PostStatus postStatus;

}
