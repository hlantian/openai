package com.zxiaoyao.openai.api.embedding;

import lombok.Data;

import java.util.List;

/**
 * @Description Represents an embedding returned by the embedding api
 * 代表嵌入式应用程序返回的嵌入信息
 * https://beta.openai.com/docs/api-reference/classifications/create
 * @Author hlantian
 * @Date Created in 13:56 2023/10/8
 * @Version 1.0
 */
@Data
public class Embedding {

    /**
     * The type of object returned, should be "embedding"
     * 返回对象的类型，应为 "embedding"。
     */
    private String object;

    /**
     * The embedding vector
     * 嵌入向量
     */
    private List<Double> embedding;

    /**
     * The position of this embedding in the list
     * 该嵌入在列表中的位置
     */
    private Integer index;
}
