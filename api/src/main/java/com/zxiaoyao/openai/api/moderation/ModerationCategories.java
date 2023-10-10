package com.zxiaoyao.openai.api.moderation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description An object containing the flags for each moderation category
 * 包含每个审核类别标志的对象
 * @Author hlantian
 * @Date Created in 10:43 2023/10/8
 * @Version 1.0
 */
@Data
public class ModerationCategories {

    private boolean hate;

    @JsonProperty("hate/threatening")
    private boolean hateThreatening;

    @JsonProperty("self-harm")
    private boolean selfHarm;

    private boolean sexual;

    @JsonProperty("sexual/minors")
    private boolean sexualMinors;

    private boolean violence;

    @JsonProperty("violence/graphic")
    private boolean violenceGraphic;
}
