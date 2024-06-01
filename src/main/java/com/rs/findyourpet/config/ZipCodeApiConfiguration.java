package com.rs.findyourpet.config;

import com.rs.findyourpet.exceptions.ApiErrorDecoder;
import com.rs.findyourpet.http.ZipCodeApiRequestHeaderInterceptor;
import com.rs.findyourpet.http.client.ZipCodeStackApi;
import feign.Client;
import feign.Feign;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZipCodeApiConfiguration {

    private final ZipCodeApiRequestHeaderInterceptor zipCodeApiRequestHeaderInterceptor;

    private final Client client;

    private final ApiErrorDecoder handler;

    @Value("${api.zipcode.url}")
    private String url;

    @Bean
    public ZipCodeStackApi zipCodeStackApi() {
        return Feign.builder()
                .client(client)
                .errorDecoder(handler)
                .logger(new Slf4jLogger())
                .requestInterceptor(zipCodeApiRequestHeaderInterceptor)
                .target(ZipCodeStackApi.class, url);
    }
}
