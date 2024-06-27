package com.zxiaoyao.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description The OpenAI resources used by a request
 * 请求使用的 OpenAI 资源
 * @Author hlantian
 * @Date Created in 14:03 2023/10/8
 * @Version 1.0
 */
@Data
public class Usage {

    /**
     * The number of prompt tokens used.
     * 使用的提示标记数量。
     */
    @JsonProperty("prompt_tokens")
    private long promptTokens;

    /**
     * The number of completion tokens used.
     * 使用的完成令牌数量。
     */
    @JsonProperty("completion_tokens")
    private long completionTokens;

    /**
     * The number of total tokens used
     * 使用的tokens总数
     */
    @JsonProperty("total_tokens")
    private long totalTokens;
}
