package co.inventorsoft.scripty.model.dto;

import java.util.Arrays;

public enum Methods {
    POST("post"),
    PUT("put"),
    GET("get");

    private String method;

    Methods(String method) {
        this.method = method;
    }

    public static boolean contains(String str) {
        return Arrays.stream(values()).anyMatch(m -> m.method.equals(str.toLowerCase()));
    }
}
