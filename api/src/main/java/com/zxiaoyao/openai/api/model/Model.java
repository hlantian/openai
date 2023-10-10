package com.zxiaoyao.openai.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description GPT model details
 * https://beta.openai.com/docs/api-reference/models
 * @Author hlantian
 * @Date Created in 10:09 2023/10/8
 * @Version 1.0
 */
@Data
public class Model {

    /**
     * An identifier for this model, used to specify the model when making completions, etc
     * 该模型的标识符，用于在进行补全等操作时指定模型
     */
    private String id;

    /**
     * The type of object returned, should be "model"
     * 返回对象的类型，应为 "model"
     */
    private String object;

    /**
     * The owner of the model, typically "openai"
     * model的所有者，通常是 "openai"
     */
    @JsonProperty("owned_by")
    private String ownedBy;

    /**
     * List of permissions for this model
     * model的permissions列表
     */
    private List<Permission> permission;

    /**
     * The root model that this and its parent (if applicable) are based on
     * 该model及其父model（如适用）所基于的根model
     */
    private String root;

    /**
     * The parent model that this is based on
     * 它所基于的父model
     */
    private String parent;
}
