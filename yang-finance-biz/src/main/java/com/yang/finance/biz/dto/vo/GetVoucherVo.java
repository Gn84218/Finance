package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class GetVoucherVo {
    /**
     *
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 凭证字配置id
     */
    @ApiModelProperty(value = "凭证字配置id")
    private Long voucherWordConfigId;

    @ApiModelProperty(value = "凭证字")
    private String voucherWord;

    /**
     * 凭证号
     */
    @ApiModelProperty(value = "凭证号")
    private Integer voucherNumber;

    /**
     * 凭证日期
     */
    @ApiModelProperty(value = "凭证日期")
    private Date voucherDate;

    /**
     * 单据数量
     */
    @ApiModelProperty(value = "单据数量")
    private Integer documentNum;

    /**
     * 凭证总金额
     */
    @ApiModelProperty(value = "凭证总金额")
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "科目明细")
    private List<VoucherSubjectDetailVo> voucherSubjectDetailVoList;

    /**
     * 凭证科目明细
     */
    @Data
    public static class VoucherSubjectDetailVo {
        @ApiModelProperty(value = "凭证科目明细id")
        private Long id;

        @ApiModelProperty(value = "凭证id")
        private Long voucherId;

        @ApiModelProperty(value = "摘要")
        private String summary;

        @ApiModelProperty(value = "科目id")
        private Long subjectId;

        @ApiModelProperty(value = "科目编码")
        private String subjectCode;

        @ApiModelProperty(value = "科目编码+科目名称+辅助核算名称")
        private String subjectFullName;

        @ApiModelProperty(value = "科目名称")
        private String subjectName;

        @ApiModelProperty(value = "显示的科目名称")
        private String showSubjectName;

        @ApiModelProperty(value = "币别id，如果未启用外币辅助核算则为0")
        private Long currencyConfigId;

        @ApiModelProperty(value = "币别名称")
        private String currencyConfigName;

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

        @ApiModelProperty(value = "是否启用数量核算配置")
        private Boolean enableNumberCalculateConfig;

        @ApiModelProperty(value = "是否启用辅助核算配置")
        private Boolean enableAssistCalculateConfigs;

        @ApiModelProperty(value = "是否启用外币核算配置")
        private Boolean enableForeignCurrencyConfig;

        @ApiModelProperty(value = "辅助核算配置")
        private List<GetVoucherVo.AssistCalculateConfigVo> assistCalculateConfigs;

        @ApiModelProperty(value = "外币核算配置")
        private List<GetVoucherVo.ForeignCurrencyConfigVo> foreignCurrencyConfig;
    }

    /**
     * 外币核算配置
     */
    @Data
    public static class ForeignCurrencyConfigVo {
        @ApiModelProperty(value = "币种id")
        private Long id;

        @ApiModelProperty(value = "币别名称")
        private String name;

        @ApiModelProperty(value = "汇率")
        private BigDecimal exchangeRate;

        @ApiModelProperty(value = "是否是本位币")
        private Boolean baseCurrencyFlag;
    }

    /**
     * 设置的辅助核算
     */
    @Data
    public static class AssistCalculateConfigVo {
        @ApiModelProperty(value = "科目id")
        private Long subjectId;

        @ApiModelProperty(value = "辅助核算类别id")
        private Long assistCalculateCateId;

        @ApiModelProperty(value = "辅助核算id")
        private Long assistCalculateId;

        @ApiModelProperty(value = "辅助核算名称")
        private String assistCalculateName;
    }
}
