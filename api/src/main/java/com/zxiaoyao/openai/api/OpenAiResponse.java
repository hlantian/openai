package com.zxiaoyao.openai.api;

import lombok.Data;

import java.util.List;

/**
 * @Description 适合 OpenAI 引擎和搜索终端的封装类
 * @Author hlantian
 * @Date Created in 16:50 2023/10/8
 * @Version 1.0
 */
@Data
public class OpenAiResponse<T> {

    /**
     * A list containing the actual results
     * 包含实际结果的列表
     */
    public List<T> data;

    /**
     * The type of object returned, should be "list"
     * 返回对象的类型，应为 "list"
     */
    public String object;
}
