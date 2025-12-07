package com.mercadona.mbordoya.web.main.application.security;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserContextHolder {
    private static final ThreadLocal<String> userContext = new ThreadLocal<>();

    public static void setUserId(String userId) {
        userContext.set(userId);
    }

    public static String getUserId() {
        return userContext.get();
    }

    public static void clear() {
        userContext.remove();
    }
}
