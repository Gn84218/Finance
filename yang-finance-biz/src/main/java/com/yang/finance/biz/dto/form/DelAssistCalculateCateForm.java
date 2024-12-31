package com.yang.finance.biz.dto.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 删除辅助核算类目
 *
 */
@Data
public class DelAssistCalculateCateForm {

    /**
     * 辅助核算类目id
     */
    @NotNull
    private Long id;
}