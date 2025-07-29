package com.example.hai_dien_church.mapper;

import com.example.hai_dien_church.dto.request.BannerRequest;
import com.example.hai_dien_church.dto.response.BannerResponse;
import com.example.hai_dien_church.entity.Banner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    Banner toBanner(BannerRequest bannerRequest);
    BannerResponse toBannerResponse(Banner banner);

}
