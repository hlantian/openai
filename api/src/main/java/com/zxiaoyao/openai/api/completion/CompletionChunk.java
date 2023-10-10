package com.zxiaoyao.openai.api.completion;

import lombok.Data;

import java.util.List;

/**
 * @Description Object containing a response chunk from the completions streaming api.
 * 包含来自完成度流api的响应块的对象。
 * https://beta.openai.com/docs/api-reference/completions/create
 * @Author hlantian
 * @Date Created in 14:39 2023/10/8
 * @Version 1.0
 */
@Data
public class CompletionChunk {

    /**
     * A unique id assigned to this completion.
     * 为该完成分配的唯一 ID。
     */
    private String id;

    /**
     * https://beta.openai.com/docs/api-reference/create-completion
     * The type of object returned, should be "text_completion"
     * 返回对象的类型应为 "text_completion"
     */
    private String object;

    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位。
     */
    private long created;

    /**
     * The model used.
     * 使用的model
     */
    private String model;

    /**
     * A list of generated completions.
     * 生成的补全列表。
     */
    private List<CompletionChoice> choices;
}
