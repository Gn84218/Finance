package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * 科目
 *
 */
@Data
public class  CreateSubjectForm {

    @ApiModelProperty(value = "父类id")
    @NotNull
    @Min(value = 1)
    private Long pid;

    @ApiModelProperty(value = "科目编码")
    @NotBlank
    @Size(min = 4, max = 16)
    @Pattern(regexp = "\\d+")
    private String code;

    @ApiModelProperty(value = "科目名称")
    @NotBlank
    @Size(max = 50)
    private String name;

    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(value = "余额方向[1：借，2：贷]")
    @NotNull
    @Range(min = 1, max = 2)
    private Integer balanceDirection;

    @ApiModelProperty(value = "是否禁用，true禁用，false启用")
    @NotNull
    private Boolean disable;

    /**
     * 核算配置[数量核算，辅助核算，外币核算]
     */
    @ApiModelProperty(value = "核算配置")
    private SubjectCalculateConfigForm subjectCalculateConfigForm;
}