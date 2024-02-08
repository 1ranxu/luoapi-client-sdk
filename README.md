<p align="center">
    <img src=./assets/logo.gif width=188/>
</p>

<h1 align="center">LUOAPI-CLIENT-SDK</h1>

<div align="center">
    <img alt="Maven" src="https://raster.shields.io/badge/Maven-3.8.1-red.svg"/>
   <img alt="SpringBoot" src="https://raster.shields.io/badge/SpringBoot-2.7+-green.svg"/>
  <a href="https://github.com/1ranxu/luoapi-client-sdk" target="_blank"><img src='https://img.shields.io/github/forks/1ranxu/luoapi-client-sdk' alt='GitHub forks' class="no-zoom"></a>
  <a href="https://github.com/1ranxu/luoapi-client-sdk" target="_blank"><img src='https://img.shields.io/github/stars/1ranxu/luoapi-client-sdk' alt='GitHub stars' class="no-zoom"></a>
</div>

**[接口空间 - 落API (luoapi.icu)](http://luoapi.icu/)**



## 🧭仓库导航


| 目录                                                         | 描述 |
| ------------------------------------------------------------ | ---- |
| [**luoapi-frontend**](https://github.com/1ranxu/luoapi-frontend) | 前端 |
| [**luoapi-backend**](https://github.com/1ranxu/luoapi-backend) | 后端 |
| **[luoapi-gateway](https://github.com/1ranxu/luoapi-gateway)** | 网关 |
| [**luoapi-interface**](https://github.com/1ranxu/luoapi-interface) | 接口 |
| **[luoapi-client-sdk](https://github.com/1ranxu/luoapi-client-sdk)** | SDK  |



## 🚀快速开始 

###  引入依赖坐标

```xml
<dependency>
    <groupId>com.luoying</groupId>
    <artifactId>luoapi-client-sdk</artifactId>
    <version>0.0.2</version>
</dependency>
```



### 前往[接口空间 - 落API (luoapi.icu)](http://luoapi.icu/)获取[开发者凭证](http://luoapi.icu/account/center)



### 初始化客户端LuoApiClient对象

- 手动实例化

  ```java
  String accessKey = "你的accessKey";
  String secretKey = "你的secretKey";
  LuoApiClient luoApiClient = new LuoApiClient(accessKey, secretKey);
  ```

- **配置文件自动实例化 推荐👍**

  - yml

    ```yml
    # LUOAPI 配置
    luoapi:
      client:
        access-key: cfccfe5100ee53776a7ba13dfaef9ef2
        secret-key: 6a65d0aa978c54aeb262d50f0711acfc
    ```



### 请求示例

`获取壁纸`

- **通过配置文件 推荐👍**

```java
@Resource
private ApiService apiService;

@Test
public void testWallPaperInterface(){
    // 构建请求参数
    Map<String, Object> params=new HashMap<>();
    params.put("method","pc");
    params.put("lx","dongman");
    
    // 配置请求对象
    LuoApiRequest luoApiRequest = new LuoApiRequest();
    // 配置请求方法
    luoApiRequest.setMethod("GET");
    // 配置请求路径（可在网站查看获取）
    luoApiRequest.setPath("http://gateway.luoapi.icu/api/randomWallpaper");
    // 配置请求参数
    luoApiRequest.setRequestParams(params);
    
    // 发起请求并打印
    log.info(apiService.request(luoApiRequest).toString());
}
```

- 手动注入
```java
@Resource
private ApiService apiService;

@Test
public void testWallPaperInterface(){
    // 手动注入客户端
    LuoApiClient luoApiClient = new LuoApiClient("你的accessKey", "你的secretKey");
    
    // 构建请求参数
    Map<String, Object> params=new HashMap<>();
    params.put("method","pc");
    params.put("lx","dongman");
    
    // 配置请求对象
    LuoApiRequest luoApiRequest = new LuoApiRequest();
    // 配置请求方法
    luoApiRequest.setMethod("GET");
    // 配置请求路径（可在网站查看获取）
    luoApiRequest.setPath("http://gateway.luoapi.icu/api/randomWallpaper");
    // 配置请求参数
    luoApiRequest.setRequestParams(params);
    
    // 发起请求并打印
    log.info(apiService.request(luoApiClient,luoApiRequest).toString());
}
```

响应：

```json
{
    "imgurl":"https://img.btstu.cn/api/images/5e747f724aba0.jpg"
}
```



## 🤝贡献 

如果您想为 **[接口空间 - 落API (luoapi.icu)](http://luoapi.icu/)**  提供好玩的接口，请随时提交issues。



## 📩联系我们 

如果您对 **[接口空间 - 落API (luoapi.icu)](http://luoapi.icu/)** 有任何问题或建议，请随时联系我们的📩邮箱：1574925401@qq.com。

