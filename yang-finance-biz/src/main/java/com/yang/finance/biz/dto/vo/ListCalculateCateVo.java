package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 辅助核算类别（表：assist_calculate_cate）
 *
 */
@Data
public class ListCalculateCateVo {
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

    @ApiModelProperty(value = "类别编码")
    private String code;
}