package com.example.hai_dien_church.repository.http_client;

import com.example.hai_dien_church.dto.request.ExchangeRequest;
import com.example.hai_dien_church.dto.response.ExchangeResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "outbound-identity",
        url = "https://oauth2.googleapis.com")
public interface OutboundIdentityClient {

    @PostMapping(value = "/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeResponse exchangeToken(@QueryMap ExchangeRequest request);
}
