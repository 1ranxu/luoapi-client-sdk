package com.luoying.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 随机壁纸响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RandomWallpaperResponse extends BaseResponse {
    private String imgurl;

    private static final long serialVersionUID = 1L;
}
