package com.zxiaoyao.openai.api.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description List of amount consumption
 * 消耗量清单
 * @Author hlantian
 * @Date Created in 15:46 2023/10/8
 * @Version 1.0
 */
@Data
public class DailyCost {

    /**
     * 时间戳
     */
    @JsonProperty("timestamp")
    private long timestamp;

    /**
     * Model consumption amount details
     * Model消耗量详情
     */
    @JsonProperty("line_items")
    private List<LineItem> lineItems;
}
