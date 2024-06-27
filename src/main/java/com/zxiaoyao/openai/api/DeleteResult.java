package com.zxiaoyao.openai.api;

import lombok.Data;

/**
 * @Description A response when deleting an object
 * 删除对象时的响应
 * @Author hlantian
 * @Date Created in 16:44 2023/10/8
 * @Version 1.0
 */
@Data
public class DeleteResult {

    /**
     * The id of the object.
     * 对象的 id。
     */
   private String id;

    /**
     * The type of object deleted, for example "file" or "model"
     * 删除对象的类型，例如 "file "或 "model
     */
    private String object;

    /**
     * True if successfully deleted
     * 如果成功删除，则为 True
     */
    private boolean deleted;
}
