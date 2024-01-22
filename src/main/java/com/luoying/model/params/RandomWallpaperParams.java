package com.luoying.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 随机壁纸请求参数
 */
@Data
@Accessors(chain = true)
public class RandomWallpaperParams implements Serializable {
    /**
     * 输出壁纸端[mobile|pc|zsy]默认为pc
     */
    private String method;
    /**
     * 选择输出分类[meizi|dongman|fengjing|suiji]，为空随机输出
     */
    private String lx;

    private static final long serialVersionUID = 1L;
}
