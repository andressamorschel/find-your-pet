package com.findyourpet.exceptions;

import lombok.Getter;

@Getter
public class HttpErrorStatusException extends RuntimeException { // TODO add in ExceptionMapperAdvice class

    private final int status;

    public HttpErrorStatusException(String message, int status) {
        super(message);
        this.status = status;
    }

}
