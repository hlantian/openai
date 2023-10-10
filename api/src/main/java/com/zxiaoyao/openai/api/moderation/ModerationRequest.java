package com.zxiaoyao.openai.api.moderation;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @Description A request for OpenAi to detect if text violates OpenAi's content policy.
 *请求 OpenAi 检测文本是否违反 OpenAi 的内容政策。
 * https://beta.openai.com/docs/api-reference/moderations/create
 * @Author hlantian
 * @Date Created in 10:46 2023/10/8
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ModerationRequest {

    /**
     * The input text to classify.
     * 要分类的输入文本。
     */
    @NonNull
    private  String input;

    /**
     * The name of the model to use, defaults to text-moderation-stable.
     * 要使用的模型名称，默认为 text-moderation-stable。
     */
    private String model;
}
