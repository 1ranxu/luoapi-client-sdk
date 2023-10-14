package com.luoying;

import com.luoying.client.LuoApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("luoapi.client")
@Data
@ComponentScan
public class LuoApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public LuoApiClient luoApiClient(){
        return new LuoApiClient(accessKey, secretKey);
    }
}
