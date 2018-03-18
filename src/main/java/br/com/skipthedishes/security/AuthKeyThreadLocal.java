package br.com.skipthedishes.security;

public class AuthKeyThreadLocal {

    private static ThreadLocal<Long> authKey = new ThreadLocal<>();

    private AuthKeyThreadLocal() {

    }

    public static Long getAuthKey() {
        return authKey.get();
    }

    public static void setAuthKey(Long key) {
        authKey.set(key);
    }
}
