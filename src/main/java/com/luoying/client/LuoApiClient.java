package com.luoying.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.luoying.model.User;
import com.luoying.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 *
 * @author 落樱的悔恨
 */
public class LuoApiClient {
    private String accessKey;
    private String secretKey;
    //API网关的域名
    private static final String GATEWAY_HOST = "http://localhost:8012";

    public LuoApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public Map<String, String> getHeaders(String body) {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        // map.put("secretKey",secretKey);不能发送
        // map.put("nonce", RandomUtil.randomNumbers(100));暂时没必要加随机数
        map.put("body", body);
        map.put("timestamp", String.valueOf(System.currentTimeMillis() + 1 * 60 * 1000));
        map.put("sign", SignUtil.genSign(body, secretKey));
        return map;
    }


    public String getUsernameByPost(User user) {
        String jsonStr = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/name/")
                .addHeaders(getHeaders(jsonStr))
                .body(jsonStr)
                .execute();
        System.out.println(response.getStatus());

        System.out.println("result = " + response.body());
        return response.body();
    }
}
