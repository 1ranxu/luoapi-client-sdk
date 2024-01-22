package com.luoying.model.request;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本api请求
 */
public abstract class BaseRequest<T> {
    private Map<String, Object> requestParams = new HashMap<>();

    /**
     * 获取请求方法
     */
    public abstract String getMethod();

    /**
     * 获取路径
     */
    public abstract String getPath();

    @JsonAnyGetter
    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(T params) {
        this.requestParams = new Gson().fromJson(JSONUtil.toJsonStr(params), new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}