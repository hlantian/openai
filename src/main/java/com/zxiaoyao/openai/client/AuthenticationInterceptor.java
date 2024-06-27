package com.zxiaoyao.openai.client;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Description OkHttp Interceptor that adds an authorization token header
 * 添加授权令牌标头的 OkHttp 拦截器
 * @Author hlantian
 * @Date Created in 9:48 2023/10/9
 * @Version 1.0
 */
public class AuthenticationInterceptor implements Interceptor {

    private final String token;

    public AuthenticationInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(request);
    }
}
