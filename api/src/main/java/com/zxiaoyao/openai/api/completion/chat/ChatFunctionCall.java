package com.zxiaoyao.openai.api.completion.chat;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 14:55 2023/10/8
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatFunctionCall {

    /**
     * The name of the function being called
     * 被调用函数的名称
     */
    private String name;

    /**
     * The arguments of the call produced by the model, represented as a JsonNode for easy manipulation.
     * 模型产生的调用参数，以 JsonNode 表示，便于操作。
     */
    private JsonNode arguments;
}
