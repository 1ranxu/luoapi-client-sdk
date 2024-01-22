package com.luoying.model.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回类
 */
@Data
@NoArgsConstructor
public class BaseResponse implements Serializable {
    private Map<String, Object> data = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    private static final long serialVersionUID = 1L;
}