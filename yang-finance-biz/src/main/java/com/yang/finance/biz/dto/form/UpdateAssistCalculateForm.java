package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 辅助核算
 *
 */
@Data
public class UpdateAssistCalculateForm {
    @NotNull
    private Long id;

    /**
     * 编码
     */
    @NotBlank
    @ApiModelProperty(value = "客户编码/供应商编码/职员编码/部门编码/项目编码/存货编码/现金流编码/编码")
    private String code;

    /**
     * 辅助核算类别名称
     */
    @NotBlank
    @ApiModelProperty(value = "客户名称/供应商名称/职员名称/部门名称/项目名称/存货名称/现金流名称/名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String notes;

    /**
     * 助记码
     */
    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;

}