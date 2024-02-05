package com.luoying.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.luoying.client.LuoApiClient;
import com.luoying.exception.ApiException;
import com.luoying.exception.ErrorCode;
import com.luoying.model.request.BaseRequest;
import com.luoying.model.response.BaseResponse;
import com.luoying.service.ApiService;
import com.luoying.utils.SignUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Api请求服务实现类
 */
@Slf4j
@Data
public class ApiServiceImpl implements ApiService {
    private LuoApiClient luoApiClient;

    /**
     * 网关域名
     */
    private static final String GATEWAY_HOST = "http://gateway.luoapi.icu";

    /**
     * @param luoApiClient 主动注入
     * @param request      请求
     */
    @Override
    public <T> BaseResponse request(LuoApiClient luoApiClient, BaseRequest<T> request) {
        checkConfig(luoApiClient);
        return request(request);
    }

    @Override
    public <T> BaseResponse request(BaseRequest<T> request) {
        try {
            return getBaseResponse(request);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    /**
     * 检查luoApiClient配置
     *
     * @param luoApiClient 主动注入
     */
    public void checkConfig(LuoApiClient luoApiClient) {
        if (luoApiClient == null) {
            throw new ApiException(ErrorCode.NO_AUTH_ERROR, "请先配置密钥AccessKey/SecretKey\"");
        }
        if (!StringUtils.isAnyBlank(luoApiClient.getAccessKey(), luoApiClient.getSecretKey())) {
            this.setLuoApiClient(luoApiClient);
        }
    }

    /**
     * 获取响应数据
     */
    public <T> BaseResponse getBaseResponse(BaseRequest<T> request) {
        if (luoApiClient == null || StringUtils.isAnyBlank(luoApiClient.getAccessKey(), luoApiClient.getSecretKey())) {
            throw new ApiException(ErrorCode.NO_AUTH_ERROR, "请先配置密钥AccessKey/SecretKey");
        }
        // 获取HttpResponse
        HttpResponse httpResponse = getHttpResponse(request);
        // 得到响应体
        String body = httpResponse.body();
        // 解析数据
        Map<String, Object> data = new HashMap<>();
        if (httpResponse.getStatus() != 200) {
            data.put("errorResult", body);
        } else {
            try {
                // 尝试解析为JSON对象
                data = new Gson().fromJson(body, new TypeToken<Map<String, Object>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                // 解析失败，将body作为普通字符串处理
                data.put("value", body);
            }
        }
        // 构建BaseResponse
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        // 返回
        return baseResponse;
    }


    /**
     * 获取HttpResponse
     */
    private <T> HttpResponse getHttpResponse(BaseRequest<T> request) {
        // 获取HttpRequest后执行，获取HttpResponse
        try (HttpResponse httpResponse = getHttpRequest(request).execute()) {
            return httpResponse;
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    /**
     * 获取HttpRequest
     */
    private <T> HttpRequest getHttpRequest(BaseRequest<T> request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求参数错误");
        }
        String path = request.getPath().trim();
        String method = request.getMethod().trim().toUpperCase();

        if (ObjectUtils.isEmpty(method)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求方法不存在");
        }
        if (StringUtils.isBlank(path)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求路径不存在");
        }
        // 请求路径去除网关域名
        if (path.startsWith(GATEWAY_HOST)) {
            path = path.substring(GATEWAY_HOST.length());
        }
        // 获取请求参数
        Map<String, Object> requestParams = request.getRequestParams();
        log.info("请求方法：{}，请求路径：{}，请求参数：{}", method, path, requestParams);
        // 构建HttpRequest
        HttpRequest httpRequest;
        switch (method) {
            case "GET": {
                httpRequest = HttpRequest.get(getUrl(request, path));
                break;
            }
            case "POST": {
                httpRequest = HttpRequest.post(GATEWAY_HOST + path);
                break;
            }
            default: {
                throw new ApiException(ErrorCode.OPERATION_ERROR, "不支持该请求");
            }
        }
        // HttpRequest设置请求头和请求体
        return httpRequest
                .addHeaders(getHeaders(JSONUtil.toJsonStr(request), luoApiClient))
                .body(JSONUtil.toJsonStr(requestParams));
    }


    /**
     * 拼接Get请求url
     */
    private <T> String getUrl(BaseRequest<T> request, String path) {
        StringBuilder urlBuilder = new StringBuilder(GATEWAY_HOST);
        urlBuilder.append(path);
        if (!request.getRequestParams().isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, Object> entry : request.getRequestParams().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                urlBuilder.append(key).append("=").append(value).append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        log.info("GET请求路径：{}", urlBuilder);
        return urlBuilder.toString();
    }


    /**
     * 获取请求头
     *
     * @param body
     * @param luoApiClient
     * @return
     */
    private Map<String, String> getHeaders(String body, LuoApiClient luoApiClient) {
        String encodedBody = SecureUtil.md5(body);
        Map<String, String> map = new HashMap<>(4);
        map.put("accessKey", luoApiClient.getAccessKey());
        map.put("body", encodedBody);
        map.put("timestamp", String.valueOf(System.currentTimeMillis() + 1 * 60 * 1000));
        map.put("sign", SignUtil.genSign(encodedBody, luoApiClient.getSecretKey()));
        return map;
    }
}
