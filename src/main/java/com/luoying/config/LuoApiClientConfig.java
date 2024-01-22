package com.luoying.config;

import com.luoying.client.LuoApiClient;
import com.luoying.service.ApiService;
import com.luoying.service.impl.ApiServiceImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("luoapi.client")
@Configuration
@ComponentScan
@Data
public class LuoApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public LuoApiClient luoApiClient() {
        return new LuoApiClient(accessKey, secretKey);
    }

    @Bean
    public ApiService apiService(LuoApiClient luoApiClient){
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.setLuoApiClient(luoApiClient);
        return apiService;
    }
}
