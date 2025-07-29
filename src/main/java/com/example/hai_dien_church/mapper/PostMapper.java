package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.PostRequest;
import com.example.hai_dien_church.dto.response.PostResponse;
import com.example.hai_dien_church.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(PostRequest postRequest);
    PostResponse toPostResponse(Post post);

}
