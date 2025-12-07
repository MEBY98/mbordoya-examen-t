package com.mercadona.mbordoya.web.main.config.interceptors.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

import static com.mercadona.mbordoya.web.main.application.utils.AppConstants.SPANISH_LOCALE;

@Configuration
public class LocaleResolverConfiguration {

    @Bean
    LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag(SPANISH_LOCALE));
        return resolver;
    }
}
