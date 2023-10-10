package com.zxiaoyao.openai.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description Model permissions
 * I couldn't find documentation for the specific permissions, and I've elected to leave them undocumented rather than
 * write something incorrect.
 *
 * https://beta.openai.com/docs/api-reference/models
 * @Author hlantian
 * @Date Created in 10:11 2023/10/8
 * @Version 1.0
 */
@Data
public class Permission {

    /**
     * An identifier for this model permission
     * ID
     */
    private String id;

    /**
     * The type of object returned, should be "model_permission"
     * 返回对象的类型应为 "model_permission"
     */
    private String object;

    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位
     */
    private long created;

    @JsonProperty("allow_create_engine")
    private boolean allowCreateEngine;

    @JsonProperty("allow_sampling")
    private boolean allowSampling;

    @JsonProperty("allow_log_probs")
    private boolean allowLogProbs;

    @JsonProperty("allow_search_indices")
    private boolean allowSearchIndices;

    @JsonProperty("allow_view")
    private boolean allowView;

    @JsonProperty("allow_fine_tuning")
    private boolean allowFineTuning;

    private String organization;

    private String group;

    @JsonProperty("is_blocking")
    private boolean isBlocking;
}
