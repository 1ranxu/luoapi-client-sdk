package com.luoying.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 名字请求参数
 */
@Data
@Accessors(chain = true)
public class NameParams implements Serializable {
    /**
     * 姓名
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
