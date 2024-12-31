package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.finance.biz.dto.form.*;
import com.yang.finance.biz.dto.vo.*;
import com.yang.finance.biz.service.AssistCalculateSummaryService;
import com.yang.mybatis.help.PageInfo;
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

@Api(tags = "辅助核算管理")
@RestController
@RequestMapping(value = "/assistCalculateSummary")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AssistCalculateSummaryController {
    final AssistCalculateSummaryService assistCalculateSummaryService;

    @ApiOperation(value = "创建自定义辅助核算")
    @PostMapping(value = "/createCustom")
    public ApiResponse<Boolean> createCustom(@Valid @RequestBody CreateAssistCalculateCustomForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "创建客户辅助核算")
    @PostMapping(value = "/createCustomer")
    public ApiResponse<Boolean> createCustomer(@Valid @RequestBody CreateAssistCalculateCustomerForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "创建部门辅助核算")
    @PostMapping(value = "/createDepartment")
    public ApiResponse<Boolean> createDepartment(@Valid @RequestBody CreateAssistCalculateDepartmentForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "创建职员辅助核算")
    @PostMapping(value = "/createEmployee")
    public ApiResponse<Boolean> createEmployee(@Valid @RequestBody CreateAssistCalculateEmployeeForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "创建存货辅助核算")
    @PostMapping(value = "/createInventory")
    public ApiResponse<Boolean> createInventory(@Valid @RequestBody CreateAssistCalculateInventoryForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "创建项目辅助核算")
    @PostMapping(value = "/createProject")
    public ApiResponse<Boolean> createProject(@Valid @RequestBody CreateAssistCalculateProjectForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "创建供应商辅助核算")
    @PostMapping(value = "/createSupplier")
    public ApiResponse<Boolean> createSupplier(@Valid @RequestBody CreateAssistCalculateSupplierForm form) {
        return ApiResponse.success(assistCalculateSummaryService.create(form));
    }

    @ApiOperation(value = "修改自定义辅助核算")
    @PostMapping(value = "/updateCustom")
    public ApiResponse<Boolean> updateCustom(@Valid @RequestBody UpdateAssistCalculateCustomForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "修改客户辅助核算")
    @PostMapping(value = "/updateCustomer")
    public ApiResponse<Boolean> updateCustomer(@Valid @RequestBody UpdateAssistCalculateCustomerForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "修改部门辅助核算")
    @PostMapping(value = "/updateDepartment")
    public ApiResponse<Boolean> updateDepartment(@Valid @RequestBody UpdateAssistCalculateDepartmentForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "修改职员辅助核算")
    @PostMapping(value = "/updateEmployee")
    public ApiResponse<Boolean> updateEmployee(@Valid @RequestBody UpdateAssistCalculateEmployeeForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "修改存货辅助核算")
    @PostMapping(value = "/updateInventory")
    public ApiResponse<Boolean> updateInventory(@Valid @RequestBody UpdateAssistCalculateInventoryForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "修改项目辅助核算")
    @PostMapping(value = "/updateProject")
    public ApiResponse<Boolean> updateProject(@Valid @RequestBody UpdateAssistCalculateProjectForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "修改供应商辅助核算")
    @PostMapping(value = "/updateSupplier")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateAssistCalculateSupplierForm form) {
        return ApiResponse.success(assistCalculateSummaryService.update(form));
    }

    @ApiOperation(value = "查询辅助核算列表")
    @PostMapping(value = "/list")
    public ApiResponse<PageInfo<ListAssistCalculateSummaryVo>> list(@Valid @RequestBody ListAssistCalculateSummaryForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form));
    }

    @ApiOperation(value = "查询自定义辅助核算列表")
    @PostMapping(value = "/listCustom")
    public ApiResponse<PageInfo<ListAssistCalculateCustomVo>> listCustom(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateCustomVo.class));
    }

    @ApiOperation(value = "查询客户辅助核算列表")
    @PostMapping(value = "/listCustomer")
    public ApiResponse<PageInfo<ListAssistCalculateCustomerVo>> listCustomer(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateCustomerVo.class));
    }

    @ApiOperation(value = "查询职员辅助核算列表")
    @PostMapping(value = "/listDepartment")
    public ApiResponse<PageInfo<ListAssistCalculateDepartmentVo>> listDepartment(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateDepartmentVo.class));
    }

    @ApiOperation(value = "查询职员辅助核算列表")
    @PostMapping(value = "/listEmployee")
    public ApiResponse<PageInfo<ListAssistCalculateEmployeeVo>> listEmployee(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateEmployeeVo.class));
    }

    @ApiOperation(value = "查询存货辅助核算列表")
    @PostMapping(value = "/listInventory")
    public ApiResponse<PageInfo<ListAssistCalculateInventoryVo>> listInventory(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateInventoryVo.class));
    }

    @ApiOperation(value = "查询项目辅助核算列表")
    @PostMapping(value = "/listProject")
    public ApiResponse<PageInfo<ListAssistCalculateProjectVo>> listProject(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateProjectVo.class));
    }

    @ApiOperation(value = "查询现金流辅助核算列表")
    @PostMapping(value = "/listCashFlow")
    public ApiResponse<PageInfo<ListAssistCalculateCashFlowVo>> listCashFlow(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateCashFlowVo.class));
    }

    @ApiOperation(value = "查询供应商辅助核算列表")
    @PostMapping(value = "/listSupplier")
    public ApiResponse<PageInfo<ListAssistCalculateSupplierVo>> listSupplier(@Valid @RequestBody ListAssistCalculateForm form) {
        return ApiResponse.success(assistCalculateSummaryService.list(form, ListAssistCalculateSupplierVo.class));
    }

    @ApiOperation(value = "获取自定义辅助核算明细")
    @GetMapping(value = "/getCustom")
    public ApiResponse<GetAssistCalculateCustomVo> getCustom(@Valid @RequestParam Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateCustomVo.class));
    }

    @ApiOperation(value = "获取客户辅助核算详情")
    @GetMapping(value = "/getCustomer")
    public ApiResponse<GetAssistCalculateCustomerVo> getCustomer(@RequestParam("id")
                                                                 @NotNull
                                                                 @Range(min = 1)
                                                                         Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateCustomerVo.class));
    }

    @ApiOperation(value = "获取部门辅助核算详情")
    @GetMapping(value = "/getDepartment")
    public ApiResponse<GetAssistCalculateDepartmentVo> getDepartment(@RequestParam("id")
                                                                     @NotNull
                                                                     @Range(min = 1)
                                                                             Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateDepartmentVo.class));
    }

    @ApiOperation(value = "获取职员辅助核算详情")
    @GetMapping(value = "/getEmployee")
    public ApiResponse<GetAssistCalculateEmployeeVo> getEmployee(@RequestParam("id")
                                                                 @NotNull
                                                                 @Range(min = 1)
                                                                         Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateEmployeeVo.class));
    }

    @ApiOperation(value = "获取存货辅助核算详情")
    @GetMapping(value = "/getInventory")
    public ApiResponse<GetAssistCalculateInventoryVo> getInventory(@RequestParam("id")
                                                                   @NotNull
                                                                   @Range(min = 1)
                                                                           Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateInventoryVo.class));
    }

    @ApiOperation(value = "获取项目辅助核算详情")
    @GetMapping(value = "/getProject")
    public ApiResponse<GetAssistCalculateProjectVo> getProject(@RequestParam("id")
                                                               @NotNull
                                                               @Range(min = 1)
                                                                       Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateProjectVo.class));
    }

    @ApiOperation(value = "获取供应商辅助核算详情")
    @GetMapping(value = "/getSupplier")
    public ApiResponse<GetAssistCalculateSupplierVo> getSupplier(@RequestParam("id")
                                                         @NotNull
                                                         @Range(min = 1)
                                                                 Long id) {
        return ApiResponse.success(assistCalculateSummaryService.get(id, GetAssistCalculateSupplierVo.class));
    }

    @ApiOperation(value = "禁用或启用辅助核算")
    @PostMapping(value = "/updateDisable")
    public ApiResponse<Boolean> updateDisable(@Valid @RequestBody UpdateDisableForm form) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateSummaryService.updateDisable(form));
    }

    @ApiOperation(value = "删除客户辅助核算")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelForm form) {
        return ApiResponse.success(assistCalculateSummaryService.del(form));
    }
}
