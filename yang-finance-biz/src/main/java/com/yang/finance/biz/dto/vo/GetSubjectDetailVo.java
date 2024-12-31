package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 科目（表：subject）
 *
 */
@Data
public class GetSubjectDetailVo {
    private Long id;

    @ApiModelProperty(value = "父类id")
    private Long pid;

    @ApiModelProperty(value = "父类名称")
    private String parentName;


    @ApiModelProperty(value = "科目编码")
    private String code;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "1：资产，2：负债，3：权益，4：成本，5：损益")
    private Integer subjectCate;

    @ApiModelProperty(value = "科目类别名称")
    private String subjectCateName;

    @ApiModelProperty(value = "科目余额")
    // todo 后面需要统计余额返回给前端
    private BigDecimal subjectBalance = BigDecimal.ZERO;

    @ApiModelProperty(value = "科目核算配置")
    private SubjectCalculateDetailConfigVo subjectCalculateConfigVo;

    @Data
    public static class SubjectCalculateDetailConfigVo {
        @ApiModelProperty(value = "是否启用数量核算配置")
        private Boolean enableNumberCalculateConfig;

        @ApiModelProperty(value = "是否启用辅助核算配置")
        private Boolean enableAssistCalculateConfigs;

        @ApiModelProperty(value = "是否启用外币核算配置")
        private Boolean enableForeignCurrencyConfig;

        @ApiModelProperty(value = "外币核算配置")
        private List<ForeignCurrencyConfigVo> foreignCurrencyConfig;

        @ApiModelProperty(value = "辅助核算配置")
        private List<AssistCalculateConfigVo> assistCalculateConfigs;

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
     * 辅助核算配置
     */
    @Data
    public static class AssistCalculateConfigVo {
        @ApiModelProperty(value = "辅助核算类别id")
        private Long id;

        @ApiModelProperty(value = "辅助核算类别名称")
        private String name;

        @ApiModelProperty(value = "辅助核算类别编码")
        private String code;

        @ApiModelProperty(value = "是否必填，true必填，false非必填")
        private Boolean requiredFlag;
    }
}