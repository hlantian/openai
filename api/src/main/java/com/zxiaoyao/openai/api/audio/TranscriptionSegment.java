package com.zxiaoyao.openai.api.audio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description An object represents transcription segment
 * 对象代表转录段
 * https://platform.openai.com/docs/api-reference/audio/create
 * @Author hlantian
 * @Date Created in 16:05 2023/10/8
 * @Version 1.0
 */
@Data
public class TranscriptionSegment {

    private Integer id;

    private Integer seek;

    private  Double start;

    private   Double end;

    private  String text;

    private List<Integer> tokens;

    private  Double temperature;

    @JsonProperty("avg_logprob")
    private   Double averageLogProb;

    @JsonProperty("compression_ratio")
    private   Double compressionRatio;

    @JsonProperty("no_speech_prob")
    private   Double noSpeechProb;

    @JsonProperty("transient")
    private  Boolean transientFlag;
}
