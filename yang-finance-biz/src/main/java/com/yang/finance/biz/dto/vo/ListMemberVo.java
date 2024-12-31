package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListMemberVo {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickName;
}