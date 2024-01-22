package com.luoying.model.request;

import lombok.experimental.Accessors;

/**
 * LuoApi请求
 */
@Accessors(chain = true)
public class LuoApiRequest extends BaseRequest<Object> {
    private String method;
    private String path;

    /**
     * 获取请求方法
     */
    @Override
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取路径
     */
    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
