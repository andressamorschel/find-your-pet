package com.findyourpet.controller;

import com.findyourpet.client.ZipCodeApiClient;
import com.findyourpet.dto.ZipCodeResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/zip-code")
public class ZipCodeController {

    private final ZipCodeApiClient zipCodeApiClient;

    @GetMapping
    public ZipCodeResultResponse getCode() {
        return zipCodeApiClient.getZipCodeInfo();
    }
}
