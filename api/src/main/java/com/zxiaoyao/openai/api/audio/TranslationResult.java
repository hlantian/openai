package com.zxiaoyao.openai.api.audio;

import lombok.Data;

import java.util.List;

/**
 * @Description An object with the English transcription
 * 有英文标音的物体
 * https://platform.openai.com/docs/api-reference/audio/create
 * @Author hlantian
 * @Date Created in 16:09 2023/10/8
 * @Version 1.0
 */
@Data
public class TranslationResult {

    /**
     * Translated text.
     * 翻译文本。
     */
    private String text;

    /**
     * Task name
     * 任务名称
     * @apiNote verbose_json response format only
     */
    private String task;

    /**
     * Translated language
     * 翻译语言
     * @apiNote verbose_json response format only
     */
    private String language;

    /**
     * Speech duration
     * 发言 持续时间
     * @apiNote verbose_json response format only
     */
    private  Double duration;

    /**
     * List of segments
     * 分段列表
     * @apiNote verbose_json response format only
     */
    private List<TranscriptionSegment> segments;
}
