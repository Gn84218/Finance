package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCateForm;
import com.yang.finance.biz.dto.form.DelAssistCalculateCateForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateCateForm;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCateVo;
import com.yang.finance.biz.dto.vo.ListCalculateCateVo;
import com.yang.finance.biz.service.AssistCalculateCateService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

@Api(tags = "辅助核算类别管理")
@RestController
@RequestMapping(value = "/assistCalculateCate")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AssistCalculateCateController {
    final AssistCalculateCateService assistCalculateCateService;

    @ApiOperation(value = "创建辅助核算类别")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateAssistCalculateCateForm form) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateCateService.create(form));
    }

    @ApiOperation(value = "删除辅助核算类别")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelAssistCalculateCateForm form) {
        return ApiResponse.success(assistCalculateCateService.del(form));
    }

    @ApiOperation(value = "修改辅助核算类别")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateAssistCalculateCateForm form) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateCateService.update(form));
    }

    @ApiOperation(value = "查询辅助核算类别列表")
    @GetMapping(value = "/list")
    public ApiResponse<List<ListCalculateCateVo>> list() {
        return ApiResponse.success(assistCalculateCateService.list());
    }

    @ApiOperation(value = "获取辅助核算类别详情")
    @GetMapping(value = "/getById")
    public ApiResponse<GetAssistCalculateCateVo> getById(@RequestParam
                                                             @NotNull
                                                             @Range(min = 1) Long id) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateCateService.getById(id));
    }
}
