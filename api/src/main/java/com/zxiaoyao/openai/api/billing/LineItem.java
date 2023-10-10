package com.zxiaoyao.openai.api.billing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description List of amount consumption
 * 消耗量清单
 * @Author hlantian
 * @Date Created in 15:45 2023/10/8
 * @Version 1.0
 */
@Data
public class LineItem {

    /**
     * model name
     * model 名称
     */
    private String name;
    /**
     * Expenditure amount
     * 支出额
     */
    private BigDecimal cost;
}
