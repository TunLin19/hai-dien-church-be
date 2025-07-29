package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.utils.CloudinaryUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CloudinaryController {

    CloudinaryUtil cloudinaryUtil;

    @PostMapping("/upload")
    public ApiResponse<CloudinaryUtil.Media> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            CloudinaryUtil.Media media = cloudinaryUtil.upload(file);
            return ApiResponse.<CloudinaryUtil.Media>builder()
                    .result(media)
                    .message("upload success")
                    .build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
