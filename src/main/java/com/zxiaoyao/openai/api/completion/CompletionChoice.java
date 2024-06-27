package com.zxiaoyao.openai.api.completion;

import lombok.Data;

/**
 * @Description Object containing a response chunk from the completions streaming api.
 * 包含来自完成度流应用程序的响应块的对象。
 * https://beta.openai.com/docs/api-reference/completions/create
 * @Author hlantian
 * @Date Created in 14:20 2023/10/8
 * @Version 1.0
 */
@Data
public class CompletionChoice {

    /**
     * The generated text. Will include the prompt if {@link CompletionRequest#echo } is true
     * 生成的文本。如果 {@link CompletionRequest#echo } 为 true，将包含提示信息
     */
    private String text;

    /**
     * This index of this completion in the returned list.
     * 返回的列表中该完成的索引。
     */
    private Integer index;

    /**
     * The log probabilities of the chosen tokens and the top {@link CompletionRequest#logprobs} tokens
     * 所选标记和顶部 {@link CompletionRequest#logprobs} 标记的对数概率
     */
    private LogProbResult logprobs;

    /**
     * The reason why GPT stopped generating, for example "length".
     * GPT 停止生成的原因，例如 "length"。
     */
    private String finish_reason;
}
