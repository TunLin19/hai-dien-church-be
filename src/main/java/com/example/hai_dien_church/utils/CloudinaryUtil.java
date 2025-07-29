package com.example.hai_dien_church.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.hai_dien_church.enums.MediaType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryUtil {

    Cloudinary cloudinary;

    public Media upload(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        assert filename != null;

        boolean isVideo = filename.toLowerCase().endsWith(".mp4");

        if (isVideo) {
            String contentType = file.getContentType();
            if (!"video/mp4".equals(contentType)) {
                throw new IllegalArgumentException("Chỉ chấp nhận video .mp4");
            }
        }

        // Upload
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", isVideo ? "video" : "image"
        ));

        Media media = new Media();
        media.setUrl((String) uploadResult.get("secure_url"));
        media.setType(isVideo ? MediaType.VIDEO : MediaType.IMAGE);
        return media;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
     public static class Media{
        String url;
        MediaType type;
     }

}
