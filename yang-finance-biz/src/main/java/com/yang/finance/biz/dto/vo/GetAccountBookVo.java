package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GetAccountBookVo {
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 统一社会信用代码
     */
    @ApiModelProperty(value = "统一社会信用代码")
    private String unifiedSocialCreditCode;

    /**
     * 行业代码id（取数据字典）
     */
    @ApiModelProperty(value = "行业代码id（取数据字典）")
    private Integer industryId;

    /**
     * 增值税种类[0：小规模纳税人；1：一般纳税人]
     */
    @ApiModelProperty(value = "增值税种类[0：小规模纳税人；1：一般纳税人]")
    private Byte valueAddedTaxCate;

    /**
     * 凭证是否审核[0：不审核；1：审核]
     */
    @ApiModelProperty(value = "凭证是否审核[0：不审核；1：审核]")
    private Boolean enableVoucherVerify;

    /**
     * 账套启用年月
     */
    @ApiModelProperty(value = "账套启用年月")
    private Date startTime;

    /**
     * 会计准则[0：小企业会计准则；1：企业会计准则；2：民间非盈利组织会计制度；3：农民专业合作社财务会计制度]
     */
    @ApiModelProperty(value = "会计准则[0：小企业会计准则；1：企业会计准则；2：民间非盈利组织会计制度；3：农民专业合作社财务会计制度]")
    private Byte accountingStandard;

    /**
     * 是否启用固定资产模块[0：不启用；1：启用]
     */
    @ApiModelProperty(value = "是否启用固定资产模块[0：不启用；1：启用]")
    private Boolean enableFixedAssets;

    /**
     * 是否启用资金模块[0：不启用；1：启用]
     */
    @ApiModelProperty(value = "是否启用资金模块[0：不启用；1：启用]")
    private Boolean enableCapital;

    /**
     * 是否启用进销存[0：不启用；1：启用]
     */
    @ApiModelProperty(value = "是否启用进销存[0：不启用；1：启用]")
    private Boolean enablePsi = false;
}
