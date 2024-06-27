package com.zxiaoyao.openai.api.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description A file uploaded to OpenAi
 * 上传到 OpenAi 的文件]
 * https://beta.openai.com/docs/api-reference/files
 * @Author hlantian
 * @Date Created in 13:53 2023/10/8
 * @Version 1.0
 */
@Data
public class File {
    /**
     * The unique id of this file.
     * 该文件的唯一 ID。
     */
    private String id;

    /**
     * The type of object returned, should be "file".
     * 返回对象的类型应为 "file"。
     */
    private String object;

    /**
     * File size in bytes.
     * 文件大小（字节）。
     */
    private Long bytes;

    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位。
     */
    @JsonProperty("created_at")
    private Long createdAt;

    /**
     * The name of the file.
     * 文件名。
     */
    private String filename;

    /**
     * Description of the file's purpose.
     * 文件用途说明。
     */
    private String purpose;
}
