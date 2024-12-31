package com.yang.finance.biz.dto.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DelVoucherForm {

    /**
     * 凭证id
     */
    @NotNull
    private Long id;

    /**
     * 凭证编号
     */
    @NotNull
    private Integer voucherNumber;
}