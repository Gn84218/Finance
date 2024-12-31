package com.yang.finance.biz.dto.vo;

import com.yang.finance.biz.dto.form.CreateAssistCalculateCateForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 辅助核算类别
 *
 */
@Data
public class GetAssistCalculateCateVo {
    /**
     *
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 辅助核算类别名称
     */
    @ApiModelProperty(value = "辅助核算类别名称")
    private String name;


    /**
     * 辅助核算类别[0：系统，不能删除，1：自定义，可自行删除]
     */
    @ApiModelProperty(value = "辅助核算类别[0：系统，不能删除，1：自定义，可自行删除]")
    private Integer level;

    @ApiModelProperty(value = "自定义列配置")
    private List<CreateAssistCalculateCateForm.CustomerColumnConfig> customerColumnConfigList;

    @Data
    public static class CustomerColumnConfig {
        @ApiModelProperty(value = "字段名称，c1,c2,c3,c4,c5,c6,c7,c8")
        private String columnName;

        private String columnAlias;
    }
}