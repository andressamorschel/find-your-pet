package com.findyourpet.client;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import com.findyourpet.exceptions.InternalServerErrorException;
import com.findyourpet.dto.ZipCodeResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ZipCodeApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.zipcode.key}")
    private String zipCodeApiKey;

    @Value("${api.zipcode.url}")
    private String zipCodeApiUrl;

    public ZipCodeResultResponse getZipCodeInfo() {
        var header = new HttpHeaders();
        header.add("apikey", zipCodeApiKey);

        var httpEntity = new HttpEntity<>(header);

        var urlTemplate = fromHttpUrl(zipCodeApiUrl)
                .queryParam("codes", "93700000")
                .queryParam("country", "br")
                .encode()
                .toUriString();

        return getCodeInfo(urlTemplate, httpEntity);
    }

    private ZipCodeResultResponse getCodeInfo(String urlTemplate, HttpEntity<Object> httpEntity) {
        try {
            return restTemplate.exchange(urlTemplate, GET, httpEntity, ZipCodeResultResponse.class).getBody();
        } catch (HttpStatusCodeException e) {
            throw new InternalServerErrorException(e, e.getMessage());
        }
    }
}
