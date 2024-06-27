package com.zxiaoyao.openai.api.embedding;

import com.zxiaoyao.openai.api.Usage;
import lombok.Data;

import java.util.List;

/**
 * @Description An object containing a response from the answer api
 * 包含应答 api 响应的对象
 * https://beta.openai.com/docs/api-reference/embeddings/create
 * @Author hlantian
 * @Date Created in 14:01 2023/10/8
 * @Version 1.0
 */
@Data
public class EmbeddingResult {

    /**
     * The GPT model used for generating embeddings
     * 用于生成嵌入模型的 GPT model
     */
    private String model;

    /**
     * The type of object returned, should be "list"
     * 返回对象的类型，应为 "list"
     */
    private String object;

    /**
     * A list of the calculated embeddings
     * 计算出的嵌入结果列表
     */
    private List<Embedding> data;

    /**
     * The API usage for this request
     */
    private Usage usage;
}
