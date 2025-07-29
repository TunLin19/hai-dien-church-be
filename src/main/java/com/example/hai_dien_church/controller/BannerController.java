package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.request.BannerRequest;
import com.example.hai_dien_church.dto.response.ApiResponse;
import com.example.hai_dien_church.dto.response.BannerResponse;
import com.example.hai_dien_church.service.BannerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BannerController {

    BannerService bannerService;

    @GetMapping
    public ApiResponse<List<BannerResponse>> getAllBanner(){
        return ApiResponse.<List<BannerResponse>>builder()
                .result(bannerService.getAllBanner())
                .build();
    }

    @PostMapping
    public ApiResponse<BannerResponse> createBanner(@RequestBody BannerRequest bannerRequest){
        return ApiResponse.<BannerResponse>builder()
                .result(bannerService.createBanner(bannerRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBanner(@PathVariable("id") String id){
        bannerService.deleteBanner(id);
        return ApiResponse.<Void>builder()
                .message("Delete success")
                .build();
    }

}
