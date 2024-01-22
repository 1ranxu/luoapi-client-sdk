package com.luoying.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 名字响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NameResponse extends BaseResponse {
    private String name;

    private static final long serialVersionUID = 1L;
}
