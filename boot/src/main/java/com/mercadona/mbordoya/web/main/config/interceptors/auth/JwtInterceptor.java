package com.mercadona.mbordoya.web.main.config.interceptors.auth;

import com.mercadona.mbordoya.web.main.application.exceptions.NotAuthorizedRequestException;
import com.mercadona.mbordoya.web.main.application.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public final class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedRequestException();
        }
        final var token = authHeader.substring(7);

        try {
            Claims claims = JwtUtil.validateToken(token);
            request.setAttribute("username", claims.getSubject());
        } catch (Exception e) {
            throw new NotAuthorizedRequestException();
        }

        return true;
    }
}
 