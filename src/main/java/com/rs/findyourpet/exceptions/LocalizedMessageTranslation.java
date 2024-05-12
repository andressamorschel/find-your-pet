package com.rs.findyourpet.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizedMessageTranslation {

    private final MessageSource messageSource;

    public String getMessage(ErrorMessage exception) {
        var locale = LocaleContextHolder.getLocale();
        var arguments = exception.getArguments().toArray(new String[0]);
        return messageSource.getMessage(exception.getMessageKey(), arguments, locale);
    }
}
