package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 创建凭证
 */
@Data
public class CreateVoucherForm {
    @ApiModelProperty(value = "凭证id，不为空且大于0表示修改凭证，否则是创建凭证")
    @Min(value = 0)
    private Long id;

    @ApiModelProperty(value = "凭证字配置id")
    @NotNull
    @Min(value = 1)
    private Long voucherWordConfigId;

    @ApiModelProperty(value = "凭证号")
    @NotNull
    @Min(value = 1)
    private Integer voucherNumber;

    @ApiModelProperty(value = "凭证日期")
    @NotNull
    private Date voucherDate;

    @ApiModelProperty(value = "单据数量")
    @NotNull
    @Min(value = 0)
    private Integer documentNum;

    @ApiModelProperty(value = "凭证总金额")
    @NotNull
    @Min(value = 1)
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "科目明细")
    @NotNull
    @Size(min = 2, max = 100)
    @Valid
    List<VoucherSubjectDetailForm> voucherSubjectDetailFormList;

    @ApiModelProperty(value = "辅助核算明细")
    @Valid
    List<VoucherSubjectAssistCalculateDetailForm> voucherSubjectAssistCalculateDetailFormList;

    /**
     * 凭证科目明细
     */
    @Data
    public static class VoucherSubjectDetailForm {
        @ApiModelProperty(value = "行编号")
        @NotNull
        @Min(value = 1)
        private Integer rowNo;

        @ApiModelProperty(value = "摘要")
        @NotBlank
        private String summary;

        @ApiModelProperty(value = "科目id")
        @NotNull
        @Min(value = 1)
        private Long subjectId;

        @ApiModelProperty(value = "币别id，如果未启用外币辅助核算则为0")
        private Long currencyConfigId;

        @ApiModelProperty(value = "汇率")
        private BigDecimal exchangeRate;

        @ApiModelProperty(value = "原币")
        private BigDecimal originalCurrency;

        @ApiModelProperty(value = "科目对应的数量")
        private Integer subjectNum;

        @ApiModelProperty(value = "科目对应的单价数量")
        private BigDecimal subjectUnitPrice;

        @ApiModelProperty(value = "余额")
        private BigDecimal balance;

        @ApiModelProperty(value = "借方金额")
        private BigDecimal debitAmount;

        @ApiModelProperty(value = "贷方金额")
        private BigDecimal creditAmount;
    }

    @Data
    public static class VoucherSubjectAssistCalculateDetailForm {
        @ApiModelProperty(value = "行编号")
        @NotNull
        @Min(value = 1)
        private Integer rowNo;

        @ApiModelProperty(value = "科目id")
        @NotNull
        @Range(min = 1)
        private Long subjectId;

        @ApiModelProperty(value = "辅助核算类别id")
        @NotNull
        @Range(min = 1)
        private Long assistCalculateCateId;

        @ApiModelProperty(value = "辅助核算id")
        @NotNull
        @Range(min = 1)
        private Long assistCalculateId;
    }
}
