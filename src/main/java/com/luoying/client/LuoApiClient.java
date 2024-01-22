package com.luoying.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用第三方接口的客户端
 *
 * @author 落樱的悔恨
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuoApiClient {
    private String accessKey;

    private String secretKey;
}
