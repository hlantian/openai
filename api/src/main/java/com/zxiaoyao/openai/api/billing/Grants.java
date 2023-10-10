package com.zxiaoyao.openai.api.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description 余额？
 * @Author hlantian
 * @Date Created in 15:52 2023/10/8
 * @Version 1.0
 */
@Data
public class Grants {

    private String object;

    @JsonProperty("data")
    private List<Datum> data;
}
