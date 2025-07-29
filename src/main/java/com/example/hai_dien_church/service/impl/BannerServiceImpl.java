package com.example.hai_dien_church.service.impl;

import com.example.hai_dien_church.dto.request.BannerRequest;
import com.example.hai_dien_church.dto.response.BannerResponse;
import com.example.hai_dien_church.entity.Banner;
import com.example.hai_dien_church.mapper.BannerMapper;
import com.example.hai_dien_church.repository.BannerRepository;
import com.example.hai_dien_church.service.BannerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BannerServiceImpl implements BannerService {

    BannerRepository bannerRepository;
    BannerMapper bannerMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public BannerResponse createBanner(BannerRequest bannerRequest) {

        Banner banner = bannerMapper.toBanner(bannerRequest);
        bannerRepository.save(banner);
        return bannerMapper.toBannerResponse(banner);
    }

    @Override
    public List<BannerResponse> getAllBanner() {
        List<Banner> list = bannerRepository.findAll();
        List<BannerResponse> bannerResponses = list
                .stream()
                .map(bannerMapper::toBannerResponse)
                .toList();
        return bannerResponses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteBanner(String id) {
        bannerRepository.deleteById(id);
    }
}
