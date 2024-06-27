package com.zxiaoyao.openai.api.completion.chat;

import com.zxiaoyao.openai.api.Usage;
import lombok.Data;

import java.util.List;

/**
 * @Description Object containing a response from the chat completions api.
 * 包含聊天完成度 api 响应的对象。
 * @Author hlantian
 * @Date Created in 15:39 2023/10/8
 * @Version 1.0
 */
@Data
public class ChatCompletionResult {

    /**
     * Unique id assigned to this chat completion.
     */
    private String id;

    /**
     * The type of object returned, should be "chat.completion"
     * 返回的对象类型应为 "chat.completion"。
     */
    private String object;

    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位。
     */
    private long created;

    /**
     * The GPT model used.
     * 所使用的 GPT model。
     */
    private String model;

    /**
     * A list of all generated completions.
     * 所有已生成完成项的列表。
     */
    private List<ChatCompletionChoice> choices;

    /**
     * The API usage for this request.
     * 该请求的 API 使用情况。
     */
    private Usage usage;

}
