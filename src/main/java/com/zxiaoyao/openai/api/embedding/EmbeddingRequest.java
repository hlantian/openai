package com.zxiaoyao.openai.api.embedding;

import lombok.*;

import java.util.List;

/**
 * @Description Creates an embedding vector representing the input text.
 * 创建代表输入文本的嵌入向量。
 * https://beta.openai.com/docs/api-reference/embeddings/create
 * @Author hlantian
 * @Date Created in 13:58 2023/10/8
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmbeddingRequest {

    /**
     * The name of the model to use.
     * Required if using the new v1/embeddings endpoint.
     * 要使用的模型名称。
     * 如果使用新的 v1/embeddings 端点，则为必填项。
     */
    private String model;

    /**
     * Input text to get embeddings for, encoded as a string or array of tokens.
     * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 2048 tokens in length.
     * <p>
     * Unless you are embedding code, we suggest replacing newlines (\n) in your input with a single space,
     * as we have observed inferior results when newlines are present.
     * 要获取嵌入信息的输入文本，编码为字符串或标记数组。
     * 若要在一次请求中获取多个输入的嵌入信息，可传递一个字符串数组或标记数组数组。
     * 每个输入的长度不得超过 2048 个标记。
     * <p>
     * 除非您要嵌入代码，否则我们建议用单个空格替换输入中的换行符 (\n)、
     * 因为我们观察到，如果出现换行符，效果将大打折扣。
     */
    @NonNull
    private List<String> input;

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     * 代表最终用户的唯一标识符，这将有助于 OpenAI 监控和检测滥用行为。
     */
    private String user;
}
