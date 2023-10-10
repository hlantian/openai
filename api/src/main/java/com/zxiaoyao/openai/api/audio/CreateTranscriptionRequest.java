package com.zxiaoyao.openai.api.audio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Description A request for OpenAi to create transcription based on an audio file All fields except model are optional
 * 请求 OpenAi 根据音频文件创建转录文件 除模型外，所有字段均为可选字段
 * https://platform.openai.com/docs/api-reference/audio/create
 * @Author hlantian
 * @Date Created in 15:58 2023/10/8
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTranscriptionRequest {


    /**
     * The name of the model to use.
     * 要使用的模型名称。
     */
    @NonNull
    private String model;

    /**
     * An optional text to guide the model's style or continue a previous audio segment. The prompt should match the audio language.
     * 可选文本，用于引导模型的风格或延续之前的音频片段。提示语应与音频语言相匹配。
     */
    private String prompt;

    /**
     * The format of the transcript output, in one of these options: json or verbose_json
     * 文本输出的格式，可使用以下选项之一：json 或 verbose_json
     */
    @JsonProperty("response_format")
    private String responseFormat;

    /**
     * The sampling temperature, between 0 and 1.
     * Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
     * If set to 0, the model will use log probability to automatically increase the temperature until certain thresholds are hit.
     * 采样temperature，介于 0 和 1 之间。
     * 较高的数值（如 0.8）会使输出更加随机，而较低的数值（如 0.2）会使输出更加集中和确定。
     * 如果设置为 0，模型将使用对数概率自动提高temperature，直到达到特定阈值。
     */
    private Double temperature;

    /**
     * The language of the input audio. Supplying the input language in ISO-639-1 format will improve accuracy and latency.
     * 输入音频的语言。提供 ISO-639-1 格式的输入语言可提高准确性和延迟。
     */
    private String language;
}
