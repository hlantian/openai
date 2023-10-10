package com.zxiaoyao.openai.api.finetuning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description An object representing an event in the lifecycle of a fine-tuning job
 * 代表微调工作生命周期中某一事件的对象
 * https://platform.openai.com/docs/api-reference/fine-tuning/list-events
 * @Author hlantian
 * @Date Created in 13:39 2023/10/8
 * @Version 1.0
 */
@Data
public class FineTuningEvent {

    /**
     * The type of object returned, should be "fine-tuneing.job.event".
     * 返回对象的类型应为 "fine-tuneing.job.event"。
     */
    private String object;

    /**
     * The creation time in epoch seconds.
     * 创建时间，以纪元秒为单位。
     */
    @JsonProperty("created_at")
    private Long createdAt;

    /**
     * The log level of this message.
     * 该信息的日志级别。
     */
    private String level;

    /**
     * The event message.
     * 事件信息。
     */
    private String message;

    /**
     * The type of event, i.e. "message"
     * 事件类型，即 "message"
     */
    private String type;
}
