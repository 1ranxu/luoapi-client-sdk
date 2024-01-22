package com.luoying.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 天气请求参数
 */
@Data
@Accessors(chain = true)
public class WeatherParams implements Serializable {
    /**
     * 城市
     */
    private String city;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 时间段
     */
    private String type;

    private static final long serialVersionUID = 1L;
}
