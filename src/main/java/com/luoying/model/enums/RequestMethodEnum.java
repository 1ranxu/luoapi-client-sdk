package com.luoying.model.enums;

/**
 * 请求方法枚举
 */
public enum RequestMethodEnum {
    /**
     * GET请求
     */
    GET("GET", "GET"),
    /**
     * POST请求
     */
    POST("POST", "POST");
    private final String text;
    private final String value;

    RequestMethodEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
