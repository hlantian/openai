package com.zxiaoyao.openai.api.moderation;

import lombok.Data;

import java.util.List;

/**
 * @Description An object containing a response from the moderation api
 * 对象，其中包含来自审核 api 的响应
 * https://beta.openai.com/docs/api-reference/moderations/create
 * @Author hlantian
 * @Date Created in 11:10 2023/10/8
 * @Version 1.0
 */
@Data
public class ModerationResult {
    /**
     * A unique id assigned to this moderation.
     * 分配给此审核的唯一 ID。
     */
    private String id;

    /**
     * The model used.
     * 使用的模型
     */
    private String model;

    /**
     * A list of moderation scores.
     * 审核分数列表。
     */
    private List<Moderation> results;
}
