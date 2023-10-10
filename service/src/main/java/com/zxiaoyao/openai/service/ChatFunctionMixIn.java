package com.zxiaoyao.openai.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Description 
 * @Author hlantian
 * @Date Created in 10:17 2023/10/9
 * @Version 1.0
 */
public abstract class ChatFunctionMixIn {

    @JsonSerialize(using = ChatFunctionParametersSerializer.class)
    abstract Class<?> getParametersClass();
}
