package com.example.hai_dien_church.service;

import com.example.hai_dien_church.dto.request.BannerRequest;
import com.example.hai_dien_church.dto.response.BannerResponse;

import java.util.List;

public interface BannerService {
    BannerResponse createBanner(BannerRequest bannerRequest);
    List<BannerResponse> getAllBanner();
    void deleteBanner(String id);
}
