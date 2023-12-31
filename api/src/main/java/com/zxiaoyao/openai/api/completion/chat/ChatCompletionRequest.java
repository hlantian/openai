package com.zxiaoyao.openai.api.completion.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author hlantian
 * @Date Created in 15:14 2023/10/8
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatCompletionRequest {


    /**
     * ID of the model to use.
     * 要使用的模型的 ID。
     */
    private String model;

    /**
     * The messages to generate chat completions for, in the <a
     * href="https://platform.openai.com/docs/guides/chat/introduction">chat format</a>.<br>
     * see {@link ChatMessage}
     */
    private List<ChatMessage> messages;

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower
     * values like 0.2 will make it more focused and deterministic.<br>
     * We generally recommend altering this or top_p but not both.
     * 使用的采样temperature，介于 0 和 2 之间。0.8 等较高值会使输出更加随机，而 0.2 等较低值则会使输出更加集中和确定。<br>
     * 一般情况下，我们建议改动此参数或 top_p，但不能同时改动。
     */
    private Double temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens
     * with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.<br>
     * We generally recommend altering this or temperature but not both.
     * 温度采样的另一种方法是核采样，模型会考虑标记的结果。的结果。因此，0.1 意味着只考虑概率质量最高的 10%的标记。<br>
     * 我们通常建议改变温度或温度，但不能同时改变。
     */
    @JsonProperty("top_p")
    private Double topP;

    /**
     * How many chat completion chatCompletionChoices to generate for each input message.
     * 为每条输入信息生成多少个聊天完成选项 chatCompletionChoices。
     */
    private Integer n;

    /**
     * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only <a
     * href="https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format">server-sent
     * events</a> as they become available, with the stream terminated by a data: [DONE] message.
     * 如果设置，将发送部分消息分隔符，就像在 ChatGPT 中一样。令牌将在可用时作为服务器发送的纯数据事件发送，并以数据流结束： [DONE] 消息终止。
     */
    private Boolean stream;

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     * 最多 4 个序列，在这些序列中，API 将停止生成更多tokens。
     */
    private List<String> stop;

    /**
     * The maximum number of tokens allowed for the generated answer. By default, the number of tokens the model can return will
     * be (4096 - prompt tokens).
     * 生成的答案所允许的最大标记数。默认情况下，模型可返回的标记数为 4096 - 提示tokens）。
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     * 介于 -2.0 和 2.0 之间的数值。正值会根据新标记是否出现在文本中对其进行惩罚、增加模型谈论新话题的可能性。
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far,
     * decreasing the model's likelihood to repeat the same line verbatim.
     * 介于 -2.0 和 2.0 之间的数值。正值会根据新标记在文本中的现有频率对其进行惩罚，从而降低模型逐字重复同一行的可能性。
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;

    /**
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value from -100
     * to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will
     * vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100
     * should result in a ban or exclusive selection of the relevant token.
     * 接受一个 json 对象，该对象将标记（由标记化器中的标记 ID 指定）映射到相关的偏置值（从 -100 到 100）。在数学上，偏置值会在采样前添加到模型生成的对数中。
     * 具体效果因模型而异，但-1 和 1 之间的值应该会降低或增加选择的可能性；-100 或 100  等值会导致禁止或排斥选择相关标记。
     */
    @JsonProperty("logit_bias")
    private Map<String, Integer> logitBias;


    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     * 代表最终用户的唯一标识符，这将有助于 OpenAI 监控和检测滥用行为。
     */
    private String user;

    /**
     * A list of the available functions.
     * 可用功能列表。
     */
    private List<ChatFunction> functions;

    /**
     * Controls how the model responds to function calls, as specified in the <a href="https://platform.openai.com/docs/api-reference/chat/create#chat/create-function_call">OpenAI documentation</a>.
     * 根据 OpenAI 文档的规定，控制模型如何响应函数调用
     */
    @JsonProperty("function_call")
    private ChatCompletionRequestFunctionCall functionCall;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatCompletionRequestFunctionCall {
        private String name;

        public static ChatCompletionRequestFunctionCall of(String name) {
            return new ChatCompletionRequestFunctionCall(name);
        }

    }
}
