package com.findyourpet.exceptions;

import static org.slf4j.helpers.MessageFormatter.arrayFormat;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(Throwable cause, String message, String... arguments) {
        super(arrayFormat(message, arguments).getMessage(), cause);
    }
}
