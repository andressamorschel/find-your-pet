package com.findyourpet.exceptions;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

    private final String messageKey;

    private final List<String> arguments;

    public static ErrorMessage error(String messageKey, String... arguments) {
        return error(messageKey, asList(arguments));
    }

    public static ErrorMessage error(String messageKey, List<String> arguments) {
        return new ErrorMessage(
                ofNullable(messageKey).filter(StringUtils::isNotBlank).orElse("internal_server_error"),
                ofNullable(arguments).orElse(emptyList())
        );
    }

}
