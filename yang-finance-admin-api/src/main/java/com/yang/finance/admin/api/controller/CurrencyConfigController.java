package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.finance.biz.dto.form.CreateCurrencyConfigForm;
import com.yang.finance.biz.dto.form.DelCurrencyConfigForm;
import com.yang.finance.biz.dto.form.UpdateCurrencyConfigForm;
import com.yang.finance.biz.dto.vo.GetCurrencyConfigVo;
import com.yang.finance.biz.dto.vo.ListCurrencyConfigVo;
import com.yang.finance.biz.service.CurrencyConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "币别设置")
@RestController
@RequestMapping(value = "/currencyConfig")
@RequiredArgsConstructor
@Slf4j
public class CurrencyConfigController {
    final CurrencyConfigService currencyConfigService;

    @ApiOperation(value = "添加币别")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateCurrencyConfigForm form) {
        return ApiResponse.success(currencyConfigService.create(form));
    }

    @ApiOperation(value = "删除币别")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelCurrencyConfigForm form) {
        return ApiResponse.success(currencyConfigService.del(form));
    }

    @ApiOperation(value = "修改币别")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateCurrencyConfigForm form) {
        return ApiResponse.success(currencyConfigService.update(form));
    }

    @ApiOperation(value = "获取币别详情")
    @GetMapping(value = "/get")
    public ApiResponse<GetCurrencyConfigVo> getById(
            @Validated
            @RequestParam
            @NotNull
            @Range(min = 1)
                    Long id) {
        return ApiResponse.success(currencyConfigService.getById(id));
    }

    @ApiOperation(value = "查询币别列表")
    @GetMapping(value = "/list")
    public ApiResponse<List<ListCurrencyConfigVo>> list() {
        return ApiResponse.success(currencyConfigService.list());
    }
}
