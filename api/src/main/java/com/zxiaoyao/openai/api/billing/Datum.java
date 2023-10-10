package com.zxiaoyao.openai.api.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 数据，资料；前提，假定；基准点
 * @Author hlantian
 * @Date Created in 15:50 2023/10/8
 * @Version 1.0
 */
@Data
public class Datum {

    private String object;
    private String id;

    /**
     * Gift amount: US dollars
     * 礼品金额： 美元
     */
    @JsonProperty("grant_amount")
    private BigDecimal grantAmount;

    /**
     * Usage amount: US dollars
     * 使用金额： 美元
     */
    @JsonProperty("used_amount")
    private BigDecimal usedAmount;

    /**
     * Effective timestamp
     * 有效时间戳
     */
    @JsonProperty("effective_at")
    private Long effectiveAt;

    /**
     * Expiration timestamp
     * 到期时间戳
     */
    @JsonProperty("expires_at")
    private Long expiresAt;
}
