package com.zxiaoyao.openai.api.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description Return value of balance inquiry interface
 * 余额查询界面的返回值
 * @Author hlantian
 * @Date Created in 15:52 2023/10/8
 * @Version 1.0
 */
@Data
public class CreditGrantsResponse implements Serializable {

    private String object;

    /**
     * Total amount: US dollars
     * 总金额 美元
     */
    @JsonProperty("total_granted")
    private BigDecimal totalGranted;

    /**
     * Total usage amount: US dollars
     * 总使用金额： 美元
     */
    @JsonProperty("total_used")
    private BigDecimal totalUsed;

    /**
     * Total remaining amount: US dollars
     * 剩余总金额： 美元
     */
    @JsonProperty("total_available")
    private BigDecimal totalAvailable;

    /**
     * Balance details
     * 余额详情
     */
    private Grants grants;
}
