package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.finance.biz.dto.form.*;
import com.yang.finance.biz.dto.vo.GetSubjectDetailVo;
import com.yang.finance.biz.dto.vo.GetSubjectVo;
import com.yang.finance.biz.dto.vo.ListSubjectByCateAndCodeAndNameVo;
import com.yang.finance.biz.dto.vo.ListSubjectVo;
import com.yang.finance.biz.service.SubjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Api(tags = "科目管理")
@RestController
@RequestMapping(value = "/subject")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SubjectController {
    final SubjectService subjectService;

    @ApiOperation(value = "创建科目")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateSubjectForm form) throws JsonProcessingException {
        return ApiResponse.success(subjectService.create(form));
    }

    @ApiOperation(value = "删除科目")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelForm form) throws JsonProcessingException {
        return ApiResponse.success(subjectService.del(form));
    }

    @ApiOperation(value = "修改科目")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateSubjectForm form) throws JsonProcessingException {
        return ApiResponse.success(subjectService.update(form));
    }

    @ApiOperation(value = "查询科目列表")
    @PostMapping(value = "/list")
    public ApiResponse<List<ListSubjectVo>> list(@Valid @RequestBody ListSubjectForm form) {
        return ApiResponse.success(subjectService.list(form));
    }

    @ApiOperation(value = "查询科目列表(根据类别编码和名称查询)")
    @PostMapping(value = "/listByCateAndCodeAndName")
    public ApiResponse<List<ListSubjectByCateAndCodeAndNameVo>> listByCateAndCodeAndName(@Valid @RequestBody ListSubjectByCateAndCodeAndNameForm form) {
        return ApiResponse.success(subjectService.list(form));
    }

    @ApiOperation(value = "获取科目详情")
    @GetMapping(value = "/get")
    public ApiResponse<GetSubjectVo> get(@RequestParam("id")
                                         @NotNull
                                         @Range(min = 1)
                                                 Long id) throws JsonProcessingException {
        return ApiResponse.success(subjectService.get(id));
    }

    @ApiOperation(value = "获取科目详情（凭证选择会计科目使用）")
    @GetMapping(value = "/getDetail")
    public ApiResponse<GetSubjectDetailVo> getDetail(@RequestParam("id")
                                         @NotNull
                                         @Range(min = 1)
                                                 Long id) throws JsonProcessingException {
        return ApiResponse.success(subjectService.getDetail(id));
    }

    @ApiOperation(value = "获取父科目详情")
    @GetMapping(value = "/getByPid")
    public ApiResponse<GetSubjectVo> getByPid(@RequestParam("pid")
                                              @NotNull
                                              @Range(min = 1)
                                                      Long pid) throws JsonProcessingException {
        return ApiResponse.success(subjectService.getByPid(pid));
    }

    @ApiOperation(value = "禁用或启用科目")
    @PostMapping(value = "/disable")
    public ApiResponse<Boolean> disable(@Valid @RequestBody DisableSubjectForm form) {
        return ApiResponse.success(subjectService.disable(form));
    }

    @ApiOperation(value = "导出")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response) throws IOException {
        subjectService.download(response);
    }
}
