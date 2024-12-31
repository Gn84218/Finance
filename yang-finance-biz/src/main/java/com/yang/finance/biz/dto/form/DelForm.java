package com.yang.finance.biz.dto.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DelForm {

    /**
     * 辅助核算类目id
     */
    @NotNull
    private Long id;
}