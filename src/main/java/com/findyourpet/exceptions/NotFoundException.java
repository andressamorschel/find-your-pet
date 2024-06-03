package com.findyourpet.exceptions;

import static com.findyourpet.exceptions.ErrorMessage.error;
import static java.lang.String.format;

import java.util.Optional;

public class NotFoundException extends RuntimeException {

    private String resourceNameKey;
    private String resourceId;

    public NotFoundException(String resourceNameKey) {
        super(format("%s not found", resourceNameKey));
        this.resourceNameKey = resourceNameKey;
    }

    public NotFoundException(String resourceNameKey, String resourceId) {
        super(format("%s %s not found", resourceNameKey, resourceId));
        this.resourceNameKey = resourceNameKey;
        this.resourceId = resourceId;
    }

    public ErrorMessage getError() {
        return Optional.ofNullable(resourceId)
                .map(id -> ErrorMessage.error(resourceNameKey, id))
                .orElseGet(() -> ErrorMessage.error(resourceNameKey));
    }
}
