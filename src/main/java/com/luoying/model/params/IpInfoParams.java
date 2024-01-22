package com.luoying.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ip归属地信息请求参数
 */
@Data
@Accessors(chain = true)
public class IpInfoParams implements Serializable {
    /**
     * ip地址
     */
    private String ip;

    private static final long serialVersionUID = 1L;
}
