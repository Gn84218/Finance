package com.yang.finance.admin.api.controller;

import com.yang
.common.dto.ApiResponse;
import com.yang.finance.biz.dto.form.*;
import com.yang.finance.biz.dto.vo.GetAccountBookVo;
import com.yang.finance.biz.dto.vo.ListAccountBookVo;
import com.yang.finance.biz.service.AccountBookService;
import com.yang.mybatis.help.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Api(tags = "账套")
@RestController
@RequestMapping(value = "/account_book")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AccountBookController {
    final AccountBookService accountBookService;

    @ApiOperation(value = "获取账套详情")
    @GetMapping(value = "/get")
    public ApiResponse<GetAccountBookVo> get(@NotNull @Min(value = 1) @RequestParam long id) {
        return ApiResponse.success(accountBookService.get(id));
    }

    @ApiOperation(value = "查询账套列表")
    @GetMapping(value = "/list")
    public ApiResponse<PageInfo<ListAccountBookVo>> list(@Validated @ModelAttribute ListAccountBookForm request) {
        return ApiResponse.success(accountBookService.list(request));
    }

    @ApiOperation(value = "创建账套")
    @PostMapping(value = "/add")
    public ApiResponse<Boolean> add(@Validated @RequestBody AddAccountBookForm request) {
        return ApiResponse.success(accountBookService.add(request));
    }

    @ApiOperation(value = "禁用启用账套")
    @PostMapping(value = "/disable")
    public ApiResponse<Boolean> disable(@Validated @RequestBody AccountBookDisableForm form) {
        return ApiResponse.success(accountBookService.disable(form));
    }

    @ApiOperation(value = "删除账套")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelForm form) {
        return ApiResponse.success(accountBookService.del(form));
    }

    @ApiOperation(value = "编辑账套")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Validated @RequestBody UpdateAccountBookForm form) {
        return ApiResponse.success(accountBookService.update(form));
    }
}
