package com.mercadona.mbordoya.web.main.config;

import com.mercadona.mbordoya.web.main.config.interceptors.auth.JwtInterceptor;
import com.mercadona.mbordoya.web.main.config.interceptors.locale.LocaleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableAsync
@Profile("!local")
public class InterceptorConfiguration implements WebMvcConfigurer {

  private final LocaleInterceptor localeInterceptor;
  private final JwtInterceptor jwtInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeInterceptor).order(Integer.MAX_VALUE);
    registry.addInterceptor(jwtInterceptor)
        .addPathPatterns("/examples", "/stores")
        .excludePathPatterns("/auth/**", "/user/**")
        .order(0);
  }

}
