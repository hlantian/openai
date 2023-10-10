package com.zxiaoyao.openai.api.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description Log probabilities of different token options Returned if {@link CompletionRequest#logprobs} is greater than zero
 * 如果 {@link CompletionRequest#logprobs} 大于零，则返回不同标记选项的日志概率
 * https://beta.openai.com/docs/api-reference/create-completion
 * @Author hlantian
 * @Date Created in 14:20 2023/10/8
 * @Version 1.0
 */
@Data
public class LogProbResult {


    /**
     * The tokens chosen by the completion api
     * 完成应用程序所选择的token
     */
    private List<String> tokens;

    /**
     * The log probability of each token in {@link LogProbResult#tokens}
     * {@link LogProbResult#tokens}中每个标记的对数概率
     */
    @JsonProperty("token_logprobs")
    private List<Double> tokenLogprobs;

    /**
     * A map for each index in the completion result.
     * The map contains the top {@link CompletionRequest#logprobs} tokens and their probabilities
     * 完成结果中每个索引的map。
     * 该map包含顶部的 {@link CompletionRequest#logprobs} 标记及其概率
     */
    @JsonProperty("top_logprobs")
    private List<Map<String, Double>> topLogprobs;

    /**
     * The character offset from the start of the returned text for each of the chosen tokens.
     * 每个所选标记从返回文本的起始位置开始的字符偏移量。
     */
    private List<Integer> textOffset;
}
