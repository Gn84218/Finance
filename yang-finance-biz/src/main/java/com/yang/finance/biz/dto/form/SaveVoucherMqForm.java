package com.yang.finance.biz.dto.form;
import lombok.Data;

@Data
public class SaveVoucherMqForm {
    /**
     * 凭证id
     */
    private Long id;

    /**
     * 消息请求id
     */
    private String requestId;
}