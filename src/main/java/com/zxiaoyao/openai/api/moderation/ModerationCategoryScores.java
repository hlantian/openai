package com.zxiaoyao.openai.api.moderation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description An object containing the scores for each moderation category
 * 包含各调节类别得分的对象
 * https://beta.openai.com/docs/api-reference/moderations/create
 * @Author hlantian
 * @Date Created in 10:45 2023/10/8
 * @Version 1.0
 */
@Data
public class ModerationCategoryScores {
    private double hate;

    @JsonProperty("hate/threatening")
    private double hateThreatening;

    @JsonProperty("self-harm")
    private double selfHarm;

    private double sexual;

    @JsonProperty("sexual/minors")
    private double sexualMinors;

    private double violence;

    @JsonProperty("violence/graphic")
    private double violenceGraphic;
}
