package com.zxiaoyao.openai.api.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description Amount consumption information
 * 消耗量信息
 * @Author hlantian
 * @Date Created in 15:44 2023/10/8
 * @Version 1.0
 */
@Data
public class BillingUsage {

    @JsonProperty("object")
    private String object;

    /**
     * Account expenditure details
     * 账户支出详情
     */
    @JsonProperty("daily_costs")
    private List<DailyCost> dailyCosts;

    /**
     * Total usage amount: cents
     * 使用总金额：美分
     */
    @JsonProperty("total_usage")
    private BigDecimal totalUsage;
}

