package com.rs.findyourpet.exceptions;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.valueOf;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();

        try {
            var stream = response.body().asInputStream();

            var bodyString = new String(stream.readAllBytes(), UTF_8);

            return new HttpErrorStatusException(bodyString, status);
        } catch (Throwable error) {
            log.error("Could not deserialize error message", error);

            var message = format("%d %s", status, valueOf(status).getReasonPhrase());

            return new HttpErrorStatusException(message, status);
        }
    }
}

