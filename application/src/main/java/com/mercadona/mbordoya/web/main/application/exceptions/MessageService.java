package com.mercadona.mbordoya.web.main.application.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messages;

    public String getMessage(final String code, final Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messages.getMessage(code, args, locale);
        } catch (NoSuchMessageException exception) {
            return code;
        }
    }
}
