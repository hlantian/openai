package com.zxiaoyao.openai.api.moderation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description An object containing the moderation data for a single input string
 * 包含单个输入字符串的调节数据的对象
 * https://beta.openai.com/docs/api-reference/moderations/create
 * @Author hlantian
 * @Date Created in 10:42 2023/10/8
 * @Version 1.0
 */
@Data
public class Moderation {
    /**
     * Set to true if the model classifies the content as violating OpenAI's content policy, false otherwise
     * 如果model将内容归类为违反 OpenAI 内容政策，则设置为 true，否则设置为 false
     */
    private boolean flagged;

    /**
     * Object containing per-category binary content policy violation flags.
     * For each category, the value is true if the model flags the corresponding category as violated, false otherwise.
     * 包含每个类别二进制内容策略违规标记的对象。
     * 对于每个类别，如果模型将相应类别标记为违规，则其值为 true，否则为 false。
     */
    private ModerationCategories categories;

    /**
     * Object containing per-category raw scores output by the model, denoting the model's confidence that the
     * input violates the OpenAI's policy for the category.
     * The value is between 0 and 1, where higher values denote higher confidence.
     * The scores should not be interpreted as probabilities.
     *  对象，包含模型输出的每个类别的原始分数，表示模型对输入违反 OpenAI
     *  输入违反 OpenAI 针对该类别的策略的可信度。
     *  分值介于 0 和 1 之间，分值越高表示置信度越高。
     *  分数不应被理解为概率。
     */
    @JsonProperty("category_scores")
    private ModerationCategoryScores categoryScores;
}
