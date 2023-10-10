package com.zxiaoyao.openai.api.completion.chat;

import lombok.Data;

import java.util.List;

/**
 * @Description Object containing a response chunk from the chat completions streaming api.
 * 包含来自聊天完成度流应用程序的响应块的对象。
 * @Author hlantian
 * @Date Created in 15:11 2023/10/8
 * @Version 1.0
 */
@Data
public class ChatCompletionChunk {

    /**
     * Unique id assigned to this chat completion.
     * 分配给此聊天完成的唯一 ID。
     */
    private String id;

    /**
     * The type of object returned, should be "chat.completion.chunk"
     * 返回的对象类型应为 "chat.completion.chunk
     */
    private String object;

    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位。
     */
    private long created;

    /**
     * The model used.
     * 使用的模型
     */
    private String model;

    /**
     * A list of all generated completions.
     * 所有已生成完成项的列表。
     */
    private List<ChatCompletionChoice> choices;
}
