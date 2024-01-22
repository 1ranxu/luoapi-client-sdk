package com.luoying.service;

import com.luoying.client.LuoApiClient;
import com.luoying.model.request.BaseRequest;
import com.luoying.model.response.BaseResponse;

/**
 * Api请求服务
 */
public interface ApiService {
    /**
     * 通用请求
     */
    <T> BaseResponse request(BaseRequest<T> request);

    /**
     * 通用请求
     */
    <T> BaseResponse request(LuoApiClient LuoApiClient, BaseRequest<T> request);

}
