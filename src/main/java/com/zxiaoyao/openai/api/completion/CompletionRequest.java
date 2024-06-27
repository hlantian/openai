package com.zxiaoyao.openai.api.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Description A request for OpenAi to generate a predicted completion for a prompt. All fields are nullable.
 * 请求 OpenAi 为一个提示生成预测完成信息。所有字段均可为空。
 * https://beta.openai.com/docs/api-reference/completions/create
 * @Author hlantian
 * @Date Created in 14:21 2023/10/8
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompletionRequest {

    /**
     * The name of the model to use.
     * Required if specifying a fine-tuned model or if using the new v1/completions endpoint.
     * 要使用的模型名称。
     * 如果指定微调模型或使用新的 v1/completions 端点，则为必填项。
     */
    private String model;

    /**
     * An optional prompt to complete from
     * 一个可选的提示，从
     */
    private String prompt;

    /**
     * The suffix that comes after a completion of inserted text.
     * 在插入文本完成后的后缀。
     */
    private String suffix;

    /**
     * The maximum number of tokens to generate.
     * Requests can use up to 2048 tokens shared between prompt and completion.
     * (One token is roughly 4 characters for normal English text)
     * 生成令牌的最大数量。
     * 请求最多可使用 2048 个标记，在提示和完成之间共享。
     * (对于普通英文文本而言，一个标记约为 4 个字符）
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * What sampling temperature to use. Higher values means the model will take more risks.
     * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
     * <p>
     * We generally recommend using this or {@link CompletionRequest#topP} but not both.
     * 使用什么采样temperature。数值越大，意味着模型将承担更多风险。
     * 对于更有创意的应用，请尝试 0.9；对于答案明确的应用，请尝试 0（最大取样）。
     * <p>
     * 我们通常建议使用此值或 {@link CompletionRequest#topP} 值，但不能同时使用这两个值。
     */
    private Double temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are
     * considered.
     * <p>
     * We generally recommend using this or {@link CompletionRequest#temperature} but not both.
     * 温度采样的另一种方法称为核采样，在这种方法中，模型考虑的结果是
     * p概率质量的代币。因此，0.1 意味着只考虑概率质量最高的 10% 的标记。
     * <p>
     * 我们通常建议使用此方法或 {@link CompletionRequest#temperature} 方法，但不能同时使用这两种方法。
     */
    @JsonProperty("top_p")
    private Double topP;

    /**
     * How many completions to generate for each prompt.
     * <p>
     * Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for {@link CompletionRequest#maxTokens} and {@link CompletionRequest#stop}.
     * 为每个提示生成多少完成项。
     * <p>
     * 由于该参数会生成大量完成信息，因此会迅速消耗你的令牌配额。
     * 请谨慎使用，并确保对 {@link CompletionRequest#maxTokens} 和 {@link CompletionRequest#stop} 进行了合理设置。
     */
    private Integer n;

    /**
     * Whether to stream back partial progress.
     * If set, tokens will be sent as data-only server-sent events as they become available,
     * with the stream terminated by a data: DONE message.
     * 是否回流部分进度。
     * 如果设置，令牌将在可用时作为服务器发送的纯数据事件发送、
     * 以数据流结束： DONE 消息终止。
     */
    private Boolean stream;

    /**
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 10, the API will return a list of the 10 most likely tokens.
     * The API will always return the logprob of the sampled token,
     * so there may be up to logprobs+1 elements in the response.
     * 包括 logprobs 最有可能的标记的日志概率，以及所选标记。
     * 例如，如果 logprobs 为 10，API 将返回 10 个最可能标记的列表。
     * API 将始终返回采样令牌的 logprob、
     * 因此，响应中最多可能包含 logprobs+1 个元素。
     */
    private Integer logprobs;

    /**
     * Echo back the prompt in addition to the completion
     * 除完成外，还回声提示
     */
    private Boolean echo;

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     * 最多 4 个序列，在这些序列中，API 将停止生成更多标记。
     * 返回的文本将不包含停止序列。
     */
    private List<String> stop;

    /**
     * Number between 0 and 1 (default 0) that penalizes new tokens based on whether they appear in the text so far.
     * Increases the model's likelihood to talk about new topics.
     * 介于 0 和 1 之间的数字（默认为 0），根据新标记是否出现在目前的文本中对其进行惩罚。
     * 增加模型谈论新话题的可能性。
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty;

    /**
     * Number between 0 and 1 (default 0) that penalizes new tokens based on their existing frequency in the text so far.
     * Decreases the model's likelihood to repeat the same line verbatim.
     * 介于 0 和 1 之间的数字（默认为 0），根据新词组目前在文本中出现的频率对其进行惩罚。
     * 降低模型逐字重复同一行的可能性。
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;

    /**
     * Generates best_of completions server-side and returns the "best"
     * (the one with the lowest log probability per token).
     * Results cannot be streamed.
     * <p>
     * When used with {@link CompletionRequest#n}, best_of controls the number of candidate completions and n specifies how many to return,
     * best_of must be greater than n.
     * 在服务器端生成 best_of completions，并返回 "best"      (每个标记的对数概率最低的那个）。
     * 结果无法流式传输。
     * <p>
     * 当与 {@link CompletionRequest#n} 一起使用时，best_of 控制候选补全的数量，而 n 则指定返回多少个、
     * best_of 必须大于 n。
     */
    @JsonProperty("best_of")
    private Integer bestOf;

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     * <p>
     * Maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100.
     * <p>
     * 修改指定标记在完成时出现的可能性。           <p>
     * 将标记（由 GPT 标记化器中的标记 ID 指定）映射到相关的偏置值（从 -100 到 100）。
     * <p>
     * https://beta.openai.com/docs/api-reference/completions/create#completions/create-logit_bias
     */
    @JsonProperty("logit_bias")
    private Map<String, Integer> logitBias;

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     * 代表最终用户的唯一标识符，这将有助于 OpenAI 监控和检测滥用行为。
     */
    private String user;
}
