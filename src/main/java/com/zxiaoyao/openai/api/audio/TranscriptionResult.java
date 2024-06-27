package com.zxiaoyao.openai.api.audio;

import lombok.Data;

import java.util.List;

/**
 * @Description An object with the text transcription
 * 带有文字转录的对象
 * https://platform.openai.com/docs/api-reference/audio/create
 * @Author hlantian
 * @Date Created in 16:04 2023/10/8
 * @Version 1.0
 */
@Data
public class TranscriptionResult {

    /**
     * The text transcription.
     * 文字转录。
     */
   private String text;

    /**
     * Task name
     * 任务名称
     * @apiNote verbose_json response format only
     */
    private String task;

    /**
     * Speech language
     * 发言 语言
     * @apiNote verbose_json response format only
     */
    private String language;

    /**
     * Speech duration
     * 发言 持续时间
     * @apiNote verbose_json response format only
     */
    private Double duration;

    /**
     * List of segments
     * 分段列表
     * @apiNote verbose_json response format only
     */
    private List<TranscriptionSegment> segments;
}
