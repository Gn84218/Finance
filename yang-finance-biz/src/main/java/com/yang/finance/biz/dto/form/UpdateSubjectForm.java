package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 修改科目
 *
 */
@Data
public class UpdateSubjectForm {

    /**
     * 科目id
     */
    @NotNull
    private Long id;

    @ApiModelProperty(value = "科目编码")
    @NotBlank
    @Size(min = 4, max = 16)
    @Pattern(regexp = "\\d+")
    private String code;

    @ApiModelProperty(value = "科目名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "助记码")
    @NotNull
    private String mnemonicCode;

    @ApiModelProperty(value = "余额方向[1：借，2：贷]")
    @NotNull
    private Integer balanceDirection;

    /**
     * 核算配置[数量核算，辅助核算，外币核算]
     */
    @ApiModelProperty(value = "核算配置")
    @NotNull
    private SubjectCalculateConfigForm subjectCalculateConfigForm;
}