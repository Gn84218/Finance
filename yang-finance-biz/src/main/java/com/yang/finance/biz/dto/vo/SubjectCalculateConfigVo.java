package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 科目核算配置
 *
 */
@Data
public class SubjectCalculateConfigVo {
    @ApiModelProperty(value = "是否启用数量核算配置")
    private Boolean enableNumberCalculateConfig;
    @ApiModelProperty(value = "数量核算配置是否从上级科目继承过来,true表示继承，前端无需传递")
    private Boolean extendParentNumberCalculateConfigFlag;
    @ApiModelProperty(value = "数量核算配置")
    private NumberCalculateConfig numberCalculateConfig;

    @ApiModelProperty(value = "是否启用辅助核算配置")
    private Boolean enableAssistCalculateConfigs;
    @ApiModelProperty(value = "辅助核算配置是否从上级科目继承过来，true表示继承，前端无需传递")
    private Boolean extendParentAssistCalculateConfigsFlag;
    @ApiModelProperty(value = "辅助核算配置")
    private List<AssistCalculateConfig> assistCalculateConfigs;

    @ApiModelProperty(value = "是否启用外币核算配置")
    private Boolean enableForeignCurrencyConfig;
    @ApiModelProperty(value = "外币辅助核算配置是否从上级科目继承过来，true表示继承，前端无需传递")
    private Boolean extendParentForeignCurrencyConfigFlag;
    @ApiModelProperty(value = "外币核算配置")
    private ForeignCurrencyConfig foreignCurrencyConfig;

    /**
     * 数量核算配置
     */
    @Data
    public static class NumberCalculateConfig {
        @ApiModelProperty(value = "计量单位")
        private String unitOfMeasurement;
    }

    /**
     * 辅助核算配置
     */
    @Data
    public static class AssistCalculateConfig {
        @ApiModelProperty(value = "辅助核算id")
        private Long assistCalculateId;

        @ApiModelProperty(value = "是否必填，true必填，false非必填")
        private Boolean requiredFlag;
    }

    /**
     * 外币核算配置
     */
    @Data
    public static class ForeignCurrencyConfig {
        @ApiModelProperty(value = "是否启用期末调汇")
        private Boolean endOfYearCurrencyRevaluationFlag;

        @ApiModelProperty(value = "币别id列表")
        private Set<Long> currencyConfigIds;

        @ApiModelProperty(value = "从上级科目继承过来的币别id列表")
        private Set<Long> parentSubjectCurrencyConfigIds;
    }
}