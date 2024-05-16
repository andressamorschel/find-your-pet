package com.rs.findyourpet.http;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZipCodeApiRequestHeaderInterceptor implements RequestInterceptor {

    @Value("${zipcodestack.api.key}")
    private String apiKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Content-Type", "application/json");
        template.query("apikey", apiKey);
    }
}
