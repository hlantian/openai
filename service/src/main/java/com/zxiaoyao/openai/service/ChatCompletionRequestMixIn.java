package com.zxiaoyao.openai.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zxiaoyao.openai.api.completion.chat.ChatCompletionRequest;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 10:19 2023/10/9
 * @Version 1.0
 */
public abstract class ChatCompletionRequestMixIn {


    @JsonSerialize(using = ChatCompletionRequestSerializerAndDeserializer.Serializer.class)
    @JsonDeserialize(using = ChatCompletionRequestSerializerAndDeserializer.Deserializer.class)
    abstract ChatCompletionRequest.ChatCompletionRequestFunctionCall getFunctionCall();
}
