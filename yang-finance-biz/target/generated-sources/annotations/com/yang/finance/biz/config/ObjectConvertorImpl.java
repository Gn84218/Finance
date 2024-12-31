package com.yang.finance.biz.config;

import com.yang.finance.biz.domain.AccountBook;
import com.yang.finance.biz.domain.AssistCalculateCashFlow;
import com.yang.finance.biz.domain.AssistCalculateCate;
import com.yang.finance.biz.domain.AssistCalculateCustom;
import com.yang.finance.biz.domain.AssistCalculateCustomer;
import com.yang.finance.biz.domain.AssistCalculateDepartment;
import com.yang.finance.biz.domain.AssistCalculateEmployee;
import com.yang.finance.biz.domain.AssistCalculateInventory;
import com.yang.finance.biz.domain.AssistCalculateProject;
import com.yang.finance.biz.domain.AssistCalculateSummary;
import com.yang.finance.biz.domain.AssistCalculateSupplier;
import com.yang.finance.biz.domain.CurrencyConfig;
import com.yang.finance.biz.domain.DataDictionary;
import com.yang.finance.biz.domain.Member;
import com.yang.finance.biz.domain.Subject;
import com.yang.finance.biz.domain.SysMenu;
import com.yang.finance.biz.domain.SysResource;
import com.yang.finance.biz.domain.SysRole;
import com.yang.finance.biz.domain.Voucher;
import com.yang.finance.biz.domain.VoucherSubjectAssistCalculateDetail;
import com.yang.finance.biz.domain.VoucherSubjectDetail;
import com.yang.finance.biz.domain.VoucherWordConfig;
import com.yang.finance.biz.domain.es.VoucherDocuemt;
import com.yang.finance.biz.dto.form.AddAccountBookForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCashFlowForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCateForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCustomForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCustomerForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateDepartmentForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateEmployeeForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateInventoryForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateProjectForm;
import com.yang.finance.biz.dto.form.CreateAssistCalculateSupplierForm;
import com.yang.finance.biz.dto.form.CreateCurrencyConfigForm;
import com.yang.finance.biz.dto.form.CreateMenuForm;
import com.yang.finance.biz.dto.form.CreateSubjectForm;
import com.yang.finance.biz.dto.form.CreateSysResourceForm;
import com.yang.finance.biz.dto.form.CreateVoucherForm;
import com.yang.finance.biz.dto.form.CreateVoucherWordConfigForm;
import com.yang.finance.biz.dto.form.SubjectCalculateConfigForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateCustomForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateCustomerForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateDepartmentForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateEmployeeForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateInventoryForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateProjectForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateSupplierForm;
import com.yang.finance.biz.dto.vo.CurrentInfoVo;
import com.yang.finance.biz.dto.vo.DataDictionaryVo;
import com.yang.finance.biz.dto.vo.DownloadSubjectVo;
import com.yang.finance.biz.dto.vo.GetAccountBookVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCashFlowVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCateVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCustomVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCustomerVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateDepartmentVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateEmployeeVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateInventoryVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateProjectVo;
import com.yang.finance.biz.dto.vo.GetAssistCalculateSupplierVo;
import com.yang.finance.biz.dto.vo.GetCurrencyConfigVo;
import com.yang.finance.biz.dto.vo.GetMenuByIdVo;
import com.yang.finance.biz.dto.vo.GetRoleDetailVo;
import com.yang.finance.biz.dto.vo.GetSubjectDetailVo;
import com.yang.finance.biz.dto.vo.GetSubjectVo;
import com.yang.finance.biz.dto.vo.GetSysResourceVo;
import com.yang.finance.biz.dto.vo.GetVoucherVo;
import com.yang.finance.biz.dto.vo.GetVoucherWordConfigVo;
import com.yang.finance.biz.dto.vo.ListAccountBookVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateCashFlowVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateCustomVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateCustomerVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateDepartmentVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateEmployeeVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateInventoryVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateProjectVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateSummaryVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateSupplierVo;
import com.yang.finance.biz.dto.vo.ListCalculateCateVo;
import com.yang.finance.biz.dto.vo.ListCurrencyConfigVo;
import com.yang.finance.biz.dto.vo.ListMemberVo;
import com.yang.finance.biz.dto.vo.ListRoleVo;
import com.yang.finance.biz.dto.vo.ListSubjectByCateAndCodeAndNameVo;
import com.yang.finance.biz.dto.vo.ListSubjectVo;
import com.yang.finance.biz.dto.vo.ListSysResourceVo;
import com.yang.finance.biz.dto.vo.ListTreeMenuVo;
import com.yang.finance.biz.dto.vo.ListTreeSelectMenuVo;
import com.yang.finance.biz.dto.vo.ListVoucherVo;
import com.yang.finance.biz.dto.vo.ListVoucherWordConfigVo;
import com.yang.finance.biz.dto.vo.MenuDataItemVo;
import com.yang.finance.biz.dto.vo.SubjectCalculateConfigVo;
import com.yang.mybatis.help.PageInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T13:07:39+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
@Component
public class ObjectConvertorImpl implements ObjectConvertor {

    @Override
    public CurrentInfoVo toCurrentInfoVo(Member source) {
        if ( source == null ) {
            return null;
        }

        CurrentInfoVo currentInfoVo = new CurrentInfoVo();

        currentInfoVo.setAvatar( source.getAvatarUrl() );
        currentInfoVo.setName( source.getName() );
        currentInfoVo.setEmail( source.getEmail() );

        return currentInfoVo;
    }

    @Override
    public ListTreeMenuVo toListTreeMenuVo(SysMenu source) {
        if ( source == null ) {
            return null;
        }

        ListTreeMenuVo listTreeMenuVo = new ListTreeMenuVo();

        if ( source.getId() != null ) {
            listTreeMenuVo.setKey( String.valueOf( source.getId() ) );
        }
        listTreeMenuVo.setTitle( source.getName() );
        listTreeMenuVo.setId( source.getId() );
        listTreeMenuVo.setIcon( source.getIcon() );
        listTreeMenuVo.setPid( source.getPid() );
        listTreeMenuVo.setPath( source.getPath() );
        listTreeMenuVo.setSort( source.getSort() );
        listTreeMenuVo.setHideInMenu( source.getHideInMenu() );

        return listTreeMenuVo;
    }

    @Override
    public List<ListTreeMenuVo> toListTreeMenuVo(List<SysMenu> source) {
        if ( source == null ) {
            return null;
        }

        List<ListTreeMenuVo> list = new ArrayList<ListTreeMenuVo>( source.size() );
        for ( SysMenu sysMenu : source ) {
            list.add( toListTreeMenuVo( sysMenu ) );
        }

        return list;
    }

    @Override
    public ListTreeSelectMenuVo toListTreeSelectMenuVo(SysMenu source) {
        if ( source == null ) {
            return null;
        }

        ListTreeSelectMenuVo listTreeSelectMenuVo = new ListTreeSelectMenuVo();

        if ( source.getId() != null ) {
            listTreeSelectMenuVo.setValue( String.valueOf( source.getId() ) );
        }
        listTreeSelectMenuVo.setTitle( source.getName() );
        listTreeSelectMenuVo.setId( source.getId() );

        return listTreeSelectMenuVo;
    }

    @Override
    public List<ListTreeSelectMenuVo> toListTreeSelectMenuVo(List<SysMenu> source) {
        if ( source == null ) {
            return null;
        }

        List<ListTreeSelectMenuVo> list = new ArrayList<ListTreeSelectMenuVo>( source.size() );
        for ( SysMenu sysMenu : source ) {
            list.add( toListTreeSelectMenuVo( sysMenu ) );
        }

        return list;
    }

    @Override
    public GetMenuByIdVo toGetMenuByIdVo(SysMenu source) {
        if ( source == null ) {
            return null;
        }

        GetMenuByIdVo getMenuByIdVo = new GetMenuByIdVo();

        getMenuByIdVo.setId( source.getId() );
        getMenuByIdVo.setPid( source.getPid() );
        getMenuByIdVo.setName( source.getName() );
        getMenuByIdVo.setPath( source.getPath() );
        getMenuByIdVo.setComponent( source.getComponent() );
        getMenuByIdVo.setIcon( source.getIcon() );
        getMenuByIdVo.setLayout( source.getLayout() );
        getMenuByIdVo.setHideInMenu( source.getHideInMenu() );
        getMenuByIdVo.setRedirect( source.getRedirect() );
        getMenuByIdVo.setSort( source.getSort() );
        getMenuByIdVo.setDisable( source.getDisable() );

        return getMenuByIdVo;
    }

    @Override
    public SysMenu toSysMenu(CreateMenuForm source) {
        if ( source == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setPid( source.getPid() );
        sysMenu.setName( source.getName() );
        sysMenu.setPath( source.getPath() );
        sysMenu.setComponent( source.getComponent() );
        sysMenu.setIcon( source.getIcon() );
        sysMenu.setLayout( source.getLayout() );
        sysMenu.setHideInMenu( source.getHideInMenu() );
        sysMenu.setRedirect( source.getRedirect() );
        sysMenu.setSort( source.getSort() );

        return sysMenu;
    }

    @Override
    public SysResource toSysResource(CreateSysResourceForm source) {
        if ( source == null ) {
            return null;
        }

        SysResource sysResource = new SysResource();

        sysResource.setPid( source.getPid() );
        sysResource.setName( source.getName() );
        sysResource.setPath( source.getPath() );

        return sysResource;
    }

    @Override
    public GetSysResourceVo toGetSysResourceVo(SysResource source) {
        if ( source == null ) {
            return null;
        }

        GetSysResourceVo getSysResourceVo = new GetSysResourceVo();

        getSysResourceVo.setId( source.getId() );
        getSysResourceVo.setName( source.getName() );
        getSysResourceVo.setPid( source.getPid() );
        getSysResourceVo.setPath( source.getPath() );

        return getSysResourceVo;
    }

    @Override
    public List<ListSysResourceVo> toListSysResourceVo(List<SysResource> source) {
        if ( source == null ) {
            return null;
        }

        List<ListSysResourceVo> list = new ArrayList<ListSysResourceVo>( source.size() );
        for ( SysResource sysResource : source ) {
            list.add( sysResourceToListSysResourceVo( sysResource ) );
        }

        return list;
    }

    @Override
    public PageInfo<ListRoleVo> toListRoleVoPage(PageInfo<SysRole> source) {
        if ( source == null ) {
            return null;
        }

        PageInfo<ListRoleVo> pageInfo = new PageInfo<ListRoleVo>();

        pageInfo.setPageNum( source.getPageNum() );
        pageInfo.setPageSize( source.getPageSize() );
        pageInfo.setTotal( source.getTotal() );
        pageInfo.setPages( source.getPages() );
        pageInfo.setList( sysRoleListToListRoleVoList( source.getList() ) );

        return pageInfo;
    }

    @Override
    public GetRoleDetailVo toGetRoleDetailVo(SysRole source) {
        if ( source == null ) {
            return null;
        }

        GetRoleDetailVo getRoleDetailVo = new GetRoleDetailVo();

        getRoleDetailVo.setId( source.getId() );
        getRoleDetailVo.setRoleName( source.getRoleName() );

        return getRoleDetailVo;
    }

    @Override
    public MenuDataItemVo toMenuDataItemVo(SysMenu source) {
        if ( source == null ) {
            return null;
        }

        MenuDataItemVo menuDataItemVo = new MenuDataItemVo();

        menuDataItemVo.setId( source.getId() );
        menuDataItemVo.setName( source.getName() );
        menuDataItemVo.setIcon( source.getIcon() );
        menuDataItemVo.setPath( source.getPath() );
        menuDataItemVo.setLayout( source.getLayout() );
        menuDataItemVo.setComponent( source.getComponent() );
        menuDataItemVo.setRedirect( source.getRedirect() );
        menuDataItemVo.setHideInMenu( source.getHideInMenu() );

        return menuDataItemVo;
    }

    @Override
    public List<MenuDataItemVo> toMenuDataItemVo(List<SysMenu> source) {
        if ( source == null ) {
            return null;
        }

        List<MenuDataItemVo> list = new ArrayList<MenuDataItemVo>( source.size() );
        for ( SysMenu sysMenu : source ) {
            list.add( toMenuDataItemVo( sysMenu ) );
        }

        return list;
    }

    @Override
    public GetAccountBookVo toGetAccountBookVo(AccountBook source) {
        if ( source == null ) {
            return null;
        }

        GetAccountBookVo getAccountBookVo = new GetAccountBookVo();

        if ( source.getId() != null ) {
            getAccountBookVo.setId( source.getId().intValue() );
        }
        getAccountBookVo.setCompanyName( source.getCompanyName() );
        getAccountBookVo.setUnifiedSocialCreditCode( source.getUnifiedSocialCreditCode() );
        getAccountBookVo.setIndustryId( source.getIndustryId() );
        getAccountBookVo.setValueAddedTaxCate( source.getValueAddedTaxCate() );
        getAccountBookVo.setEnableVoucherVerify( source.getEnableVoucherVerify() );
        getAccountBookVo.setStartTime( source.getStartTime() );
        getAccountBookVo.setAccountingStandard( source.getAccountingStandard() );
        getAccountBookVo.setEnableFixedAssets( source.getEnableFixedAssets() );
        getAccountBookVo.setEnableCapital( source.getEnableCapital() );
        getAccountBookVo.setEnablePsi( source.getEnablePsi() );

        return getAccountBookVo;
    }

    @Override
    public ListAccountBookVo toListAccountBookVo(AccountBook source) {
        if ( source == null ) {
            return null;
        }

        ListAccountBookVo listAccountBookVo = new ListAccountBookVo();

        if ( source.getId() != null ) {
            listAccountBookVo.setId( source.getId().intValue() );
        }
        listAccountBookVo.setCompanyName( source.getCompanyName() );
        listAccountBookVo.setStartTime( source.getStartTime() );
        listAccountBookVo.setCreateTime( source.getCreateTime() );
        listAccountBookVo.setEnableVoucherVerify( source.getEnableVoucherVerify() );
        listAccountBookVo.setDisable( source.getDisable() );

        listAccountBookVo.setValueAddedTaxCate( com.yang.finance.biz.enums.ValueAddedTaxCateEnum.getMessage(source.getValueAddedTaxCate()) );
        listAccountBookVo.setAccountingStandard( com.yang.finance.biz.enums.AccountingStandardEnum.getMessage(source.getAccountingStandard()) );

        return listAccountBookVo;
    }

    @Override
    public PageInfo<ListAccountBookVo> toListAccountBookVoPage(PageInfo<AccountBook> source) {
        if ( source == null ) {
            return null;
        }

        PageInfo<ListAccountBookVo> pageInfo = new PageInfo<ListAccountBookVo>();

        pageInfo.setPageNum( source.getPageNum() );
        pageInfo.setPageSize( source.getPageSize() );
        pageInfo.setTotal( source.getTotal() );
        pageInfo.setPages( source.getPages() );
        pageInfo.setList( accountBookListToListAccountBookVoList( source.getList() ) );

        return pageInfo;
    }

    @Override
    public AccountBook toAccountBook(AddAccountBookForm source) {
        if ( source == null ) {
            return null;
        }

        AccountBook accountBook = new AccountBook();

        accountBook.setCompanyName( source.getCompanyName() );
        accountBook.setUnifiedSocialCreditCode( source.getUnifiedSocialCreditCode() );
        accountBook.setIndustryId( source.getIndustryId() );
        accountBook.setValueAddedTaxCate( source.getValueAddedTaxCate() );
        accountBook.setEnableVoucherVerify( source.getEnableVoucherVerify() );
        accountBook.setStartTime( source.getStartTime() );
        accountBook.setAccountingStandard( source.getAccountingStandard() );
        accountBook.setEnableFixedAssets( source.getEnableFixedAssets() );
        accountBook.setEnableCapital( source.getEnableCapital() );
        accountBook.setEnablePsi( source.getEnablePsi() );

        return accountBook;
    }

    @Override
    public List<DataDictionaryVo> toDataDictionaryVo(List<DataDictionary> source) {
        if ( source == null ) {
            return null;
        }

        List<DataDictionaryVo> list = new ArrayList<DataDictionaryVo>( source.size() );
        for ( DataDictionary dataDictionary : source ) {
            list.add( dataDictionaryToDataDictionaryVo( dataDictionary ) );
        }

        return list;
    }

    @Override
    public List<ListCurrencyConfigVo> toListCurrencyConfigVo(List<CurrencyConfig> source) {
        if ( source == null ) {
            return null;
        }

        List<ListCurrencyConfigVo> list = new ArrayList<ListCurrencyConfigVo>( source.size() );
        for ( CurrencyConfig currencyConfig : source ) {
            list.add( currencyConfigToListCurrencyConfigVo( currencyConfig ) );
        }

        return list;
    }

    @Override
    public CurrencyConfig toCurrencyConfig(CreateCurrencyConfigForm source) {
        if ( source == null ) {
            return null;
        }

        CurrencyConfig currencyConfig = new CurrencyConfig();

        currencyConfig.setCode( source.getCode() );
        currencyConfig.setName( source.getName() );
        currencyConfig.setExchangeRate( source.getExchangeRate() );

        return currencyConfig;
    }

    @Override
    public GetCurrencyConfigVo toGetCurrencyConfigVo(CurrencyConfig source) {
        if ( source == null ) {
            return null;
        }

        GetCurrencyConfigVo getCurrencyConfigVo = new GetCurrencyConfigVo();

        getCurrencyConfigVo.setId( source.getId() );
        getCurrencyConfigVo.setCode( source.getCode() );
        getCurrencyConfigVo.setName( source.getName() );
        getCurrencyConfigVo.setExchangeRate( source.getExchangeRate() );
        getCurrencyConfigVo.setDisable( source.getDisable() );
        getCurrencyConfigVo.setBaseCurrencyFlag( source.getBaseCurrencyFlag() );

        return getCurrencyConfigVo;
    }

    @Override
    public VoucherWordConfig toVoucherWordConfig(CreateVoucherWordConfigForm source) {
        if ( source == null ) {
            return null;
        }

        VoucherWordConfig voucherWordConfig = new VoucherWordConfig();

        voucherWordConfig.setVoucherWord( source.getVoucherWord() );
        voucherWordConfig.setPrintTitle( source.getPrintTitle() );

        return voucherWordConfig;
    }

    @Override
    public GetVoucherWordConfigVo toGetVoucherWordConfigVo(VoucherWordConfig source) {
        if ( source == null ) {
            return null;
        }

        GetVoucherWordConfigVo getVoucherWordConfigVo = new GetVoucherWordConfigVo();

        if ( source.getId() != null ) {
            getVoucherWordConfigVo.setId( source.getId().intValue() );
        }
        getVoucherWordConfigVo.setVoucherWord( source.getVoucherWord() );
        getVoucherWordConfigVo.setPrintTitle( source.getPrintTitle() );
        getVoucherWordConfigVo.setDefaultFlag( source.getDefaultFlag() );

        return getVoucherWordConfigVo;
    }

    @Override
    public List<ListVoucherWordConfigVo> toListVoucherWordConfigVo(List<VoucherWordConfig> source) {
        if ( source == null ) {
            return null;
        }

        List<ListVoucherWordConfigVo> list = new ArrayList<ListVoucherWordConfigVo>( source.size() );
        for ( VoucherWordConfig voucherWordConfig : source ) {
            list.add( voucherWordConfigToListVoucherWordConfigVo( voucherWordConfig ) );
        }

        return list;
    }

    @Override
    public List<ListCalculateCateVo> toListCalculateCateVos(List<AssistCalculateCate> source) {
        if ( source == null ) {
            return null;
        }

        List<ListCalculateCateVo> list = new ArrayList<ListCalculateCateVo>( source.size() );
        for ( AssistCalculateCate assistCalculateCate : source ) {
            list.add( assistCalculateCateToListCalculateCateVo( assistCalculateCate ) );
        }

        return list;
    }

    @Override
    public AssistCalculateCate toAssistCalculateCate(CreateAssistCalculateCateForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateCate assistCalculateCate = new AssistCalculateCate();

        assistCalculateCate.setName( source.getName() );

        return assistCalculateCate;
    }

    @Override
    public GetAssistCalculateCateVo toGetAssistCalculateCateVo(AssistCalculateCate source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateCateVo getAssistCalculateCateVo = new GetAssistCalculateCateVo();

        if ( source.getId() != null ) {
            getAssistCalculateCateVo.setId( source.getId().intValue() );
        }
        getAssistCalculateCateVo.setName( source.getName() );
        getAssistCalculateCateVo.setLevel( source.getLevel() );

        return getAssistCalculateCateVo;
    }

    @Override
    public PageInfo<ListAssistCalculateSummaryVo> toListAssistCalculateSummaryVo(PageInfo<AssistCalculateSummary> source) {
        if ( source == null ) {
            return null;
        }

        PageInfo<ListAssistCalculateSummaryVo> pageInfo = new PageInfo<ListAssistCalculateSummaryVo>();

        pageInfo.setPageNum( source.getPageNum() );
        pageInfo.setPageSize( source.getPageSize() );
        pageInfo.setTotal( source.getTotal() );
        pageInfo.setPages( source.getPages() );
        pageInfo.setList( assistCalculateSummaryListToListAssistCalculateSummaryVoList( source.getList() ) );

        return pageInfo;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(CreateAssistCalculateForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setDisable( source.getDisable() );
        assistCalculateSummary.setAssistCalculateCateId( source.getAssistCalculateCateId() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateCustomerForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateCustomForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateDepartmentForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateEmployeeForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateInventoryForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateProjectForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateSummary toAssistCalculateSummary(UpdateAssistCalculateSupplierForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSummary assistCalculateSummary = new AssistCalculateSummary();

        assistCalculateSummary.setId( source.getId() );
        assistCalculateSummary.setName( source.getName() );
        assistCalculateSummary.setCode( source.getCode() );
        assistCalculateSummary.setMnemonicCode( source.getMnemonicCode() );
        assistCalculateSummary.setNotes( source.getNotes() );

        return assistCalculateSummary;
    }

    @Override
    public AssistCalculateCashFlow toAssistCalculateCashFlow(CreateAssistCalculateCashFlowForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateCashFlow assistCalculateCashFlow = new AssistCalculateCashFlow();

        assistCalculateCashFlow.setCashFlowCate( source.getCashFlowCate() );
        assistCalculateCashFlow.setNotes( source.getNotes() );
        assistCalculateCashFlow.setDisable( source.getDisable() );
        assistCalculateCashFlow.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateCashFlow;
    }

    @Override
    public List<ListAssistCalculateCashFlowVo> toListAssistCalculateCashFlowVo(List<AssistCalculateCashFlow> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateCashFlowVo> list = new ArrayList<ListAssistCalculateCashFlowVo>( source.size() );
        for ( AssistCalculateCashFlow assistCalculateCashFlow : source ) {
            list.add( assistCalculateCashFlowToListAssistCalculateCashFlowVo( assistCalculateCashFlow ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateCashFlowVo toGetAssistCalculateCashFlowVo(AssistCalculateCashFlow source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateCashFlowVo getAssistCalculateCashFlowVo = new GetAssistCalculateCashFlowVo();

        if ( source.getId() != null ) {
            getAssistCalculateCashFlowVo.setId( source.getId().intValue() );
        }
        getAssistCalculateCashFlowVo.setCashFlowCate( source.getCashFlowCate() );
        getAssistCalculateCashFlowVo.setNotes( source.getNotes() );

        return getAssistCalculateCashFlowVo;
    }

    @Override
    public AssistCalculateCustomer toAssistCalculateCustomer(CreateAssistCalculateCustomerForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateCustomer assistCalculateCustomer = new AssistCalculateCustomer();

        assistCalculateCustomer.setCustomerCate( source.getCustomerCate() );
        assistCalculateCustomer.setAddress( source.getAddress() );
        assistCalculateCustomer.setContacts( source.getContacts() );
        assistCalculateCustomer.setPhone( source.getPhone() );
        assistCalculateCustomer.setDisable( source.getDisable() );
        assistCalculateCustomer.setUnifiedSocialCreditCode( source.getUnifiedSocialCreditCode() );
        assistCalculateCustomer.setProvinceCode( source.getProvinceCode() );
        assistCalculateCustomer.setCityCode( source.getCityCode() );
        assistCalculateCustomer.setCountyCode( source.getCountyCode() );
        assistCalculateCustomer.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateCustomer;
    }

    @Override
    public List<ListAssistCalculateCustomerVo> toListAssistCalculateCustomerVo(List<AssistCalculateCustomer> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateCustomerVo> list = new ArrayList<ListAssistCalculateCustomerVo>( source.size() );
        for ( AssistCalculateCustomer assistCalculateCustomer : source ) {
            list.add( assistCalculateCustomerToListAssistCalculateCustomerVo( assistCalculateCustomer ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateCustomerVo toGetAssistCalculateCustomerVo(AssistCalculateCustomer source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateCustomerVo getAssistCalculateCustomerVo = new GetAssistCalculateCustomerVo();

        getAssistCalculateCustomerVo.setId( source.getId() );
        getAssistCalculateCustomerVo.setCustomerCate( source.getCustomerCate() );
        getAssistCalculateCustomerVo.setAddress( source.getAddress() );
        getAssistCalculateCustomerVo.setContacts( source.getContacts() );
        getAssistCalculateCustomerVo.setPhone( source.getPhone() );
        getAssistCalculateCustomerVo.setUnifiedSocialCreditCode( source.getUnifiedSocialCreditCode() );
        getAssistCalculateCustomerVo.setProvinceCode( source.getProvinceCode() );
        getAssistCalculateCustomerVo.setCityCode( source.getCityCode() );
        getAssistCalculateCustomerVo.setCountyCode( source.getCountyCode() );

        return getAssistCalculateCustomerVo;
    }

    @Override
    public AssistCalculateCustom toAssistCalculateCustom(CreateAssistCalculateCustomForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateCustom assistCalculateCustom = new AssistCalculateCustom();

        assistCalculateCustom.setC1( source.getC1() );
        assistCalculateCustom.setC2( source.getC2() );
        assistCalculateCustom.setC3( source.getC3() );
        assistCalculateCustom.setC4( source.getC4() );
        assistCalculateCustom.setC5( source.getC5() );
        assistCalculateCustom.setC6( source.getC6() );
        assistCalculateCustom.setC7( source.getC7() );
        assistCalculateCustom.setC8( source.getC8() );
        assistCalculateCustom.setC9( source.getC9() );
        assistCalculateCustom.setDisable( source.getDisable() );
        assistCalculateCustom.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateCustom;
    }

    @Override
    public List<ListAssistCalculateCustomVo> toListAssistCalculateVo(List<AssistCalculateCustom> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateCustomVo> list = new ArrayList<ListAssistCalculateCustomVo>( source.size() );
        for ( AssistCalculateCustom assistCalculateCustom : source ) {
            list.add( assistCalculateCustomToListAssistCalculateCustomVo( assistCalculateCustom ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateCustomVo toGetAssistCalculateCustomVo(AssistCalculateCustom source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateCustomVo getAssistCalculateCustomVo = new GetAssistCalculateCustomVo();

        getAssistCalculateCustomVo.setId( source.getId() );
        getAssistCalculateCustomVo.setC1( source.getC1() );
        getAssistCalculateCustomVo.setC2( source.getC2() );
        getAssistCalculateCustomVo.setC3( source.getC3() );
        getAssistCalculateCustomVo.setC4( source.getC4() );
        getAssistCalculateCustomVo.setC5( source.getC5() );
        getAssistCalculateCustomVo.setC6( source.getC6() );
        getAssistCalculateCustomVo.setC7( source.getC7() );
        getAssistCalculateCustomVo.setC8( source.getC8() );

        return getAssistCalculateCustomVo;
    }

    @Override
    public AssistCalculateDepartment toAssistCalculateDepartment(CreateAssistCalculateDepartmentForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateDepartment assistCalculateDepartment = new AssistCalculateDepartment();

        assistCalculateDepartment.setManager( source.getManager() );
        assistCalculateDepartment.setPhone( source.getPhone() );
        assistCalculateDepartment.setStartDate( source.getStartDate() );
        assistCalculateDepartment.setRevokeDate( source.getRevokeDate() );
        assistCalculateDepartment.setDisable( source.getDisable() );
        assistCalculateDepartment.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateDepartment;
    }

    @Override
    public List<ListAssistCalculateDepartmentVo> toAssistCalculateDepartmentVo(List<AssistCalculateDepartment> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateDepartmentVo> list = new ArrayList<ListAssistCalculateDepartmentVo>( source.size() );
        for ( AssistCalculateDepartment assistCalculateDepartment : source ) {
            list.add( assistCalculateDepartmentToListAssistCalculateDepartmentVo( assistCalculateDepartment ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateDepartmentVo toGetAssistCalculateDepartmentVo(AssistCalculateDepartment source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateDepartmentVo getAssistCalculateDepartmentVo = new GetAssistCalculateDepartmentVo();

        getAssistCalculateDepartmentVo.setId( source.getId() );
        getAssistCalculateDepartmentVo.setManager( source.getManager() );
        getAssistCalculateDepartmentVo.setPhone( source.getPhone() );
        getAssistCalculateDepartmentVo.setStartDate( source.getStartDate() );
        getAssistCalculateDepartmentVo.setRevokeDate( source.getRevokeDate() );

        return getAssistCalculateDepartmentVo;
    }

    @Override
    public AssistCalculateEmployee toAssistCalculateEmployee(CreateAssistCalculateEmployeeForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateEmployee assistCalculateEmployee = new AssistCalculateEmployee();

        assistCalculateEmployee.setSex( source.getSex() );
        assistCalculateEmployee.setDepartmentCode( source.getDepartmentCode() );
        assistCalculateEmployee.setDepartmentName( source.getDepartmentName() );
        assistCalculateEmployee.setPosition( source.getPosition() );
        assistCalculateEmployee.setJob( source.getJob() );
        assistCalculateEmployee.setPhone( source.getPhone() );
        assistCalculateEmployee.setBirthday( source.getBirthday() );
        assistCalculateEmployee.setStartDate( source.getStartDate() );
        assistCalculateEmployee.setDepartureDate( source.getDepartureDate() );
        assistCalculateEmployee.setDisable( source.getDisable() );
        assistCalculateEmployee.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateEmployee;
    }

    @Override
    public List<ListAssistCalculateEmployeeVo> toAssistCalculateEmployee(List<AssistCalculateEmployee> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateEmployeeVo> list = new ArrayList<ListAssistCalculateEmployeeVo>( source.size() );
        for ( AssistCalculateEmployee assistCalculateEmployee : source ) {
            list.add( assistCalculateEmployeeToListAssistCalculateEmployeeVo( assistCalculateEmployee ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateEmployeeVo toGetAssistCalculateEmployeeVo(AssistCalculateEmployee source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateEmployeeVo getAssistCalculateEmployeeVo = new GetAssistCalculateEmployeeVo();

        getAssistCalculateEmployeeVo.setId( source.getId() );
        getAssistCalculateEmployeeVo.setSex( source.getSex() );
        getAssistCalculateEmployeeVo.setDepartmentCode( source.getDepartmentCode() );
        getAssistCalculateEmployeeVo.setDepartmentName( source.getDepartmentName() );
        getAssistCalculateEmployeeVo.setPosition( source.getPosition() );
        getAssistCalculateEmployeeVo.setJob( source.getJob() );
        getAssistCalculateEmployeeVo.setPhone( source.getPhone() );
        getAssistCalculateEmployeeVo.setBirthday( source.getBirthday() );
        getAssistCalculateEmployeeVo.setStartDate( source.getStartDate() );
        getAssistCalculateEmployeeVo.setDepartureDate( source.getDepartureDate() );

        return getAssistCalculateEmployeeVo;
    }

    @Override
    public AssistCalculateInventory toAssistCalculateInventory(CreateAssistCalculateInventoryForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateInventory assistCalculateInventory = new AssistCalculateInventory();

        assistCalculateInventory.setSpecifications( source.getSpecifications() );
        assistCalculateInventory.setInventoryCate( source.getInventoryCate() );
        assistCalculateInventory.setUnits( source.getUnits() );
        assistCalculateInventory.setStartDate( source.getStartDate() );
        assistCalculateInventory.setEndDate( source.getEndDate() );
        assistCalculateInventory.setDisable( source.getDisable() );
        assistCalculateInventory.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateInventory;
    }

    @Override
    public List<ListAssistCalculateInventoryVo> toListAssistCalculateInventoryVo(List<AssistCalculateInventory> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateInventoryVo> list = new ArrayList<ListAssistCalculateInventoryVo>( source.size() );
        for ( AssistCalculateInventory assistCalculateInventory : source ) {
            list.add( assistCalculateInventoryToListAssistCalculateInventoryVo( assistCalculateInventory ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateInventoryVo toGetAssistCalculateInventoryVo(AssistCalculateInventory source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateInventoryVo getAssistCalculateInventoryVo = new GetAssistCalculateInventoryVo();

        getAssistCalculateInventoryVo.setId( source.getId() );
        getAssistCalculateInventoryVo.setSpecifications( source.getSpecifications() );
        getAssistCalculateInventoryVo.setInventoryCate( source.getInventoryCate() );
        getAssistCalculateInventoryVo.setUnits( source.getUnits() );
        getAssistCalculateInventoryVo.setStartDate( source.getStartDate() );
        getAssistCalculateInventoryVo.setEndDate( source.getEndDate() );

        return getAssistCalculateInventoryVo;
    }

    @Override
    public AssistCalculateProject toAssistCalculateProject(CreateAssistCalculateProjectForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateProject assistCalculateProject = new AssistCalculateProject();

        assistCalculateProject.setResponsibleDepartment( source.getResponsibleDepartment() );
        assistCalculateProject.setResponsiblePerson( source.getResponsiblePerson() );
        assistCalculateProject.setPhone( source.getPhone() );
        assistCalculateProject.setStartDate( source.getStartDate() );
        assistCalculateProject.setEndDate( source.getEndDate() );
        assistCalculateProject.setDisable( source.getDisable() );
        assistCalculateProject.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateProject;
    }

    @Override
    public List<ListAssistCalculateProjectVo> toListAssistCalculateProjectVo(List<AssistCalculateProject> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateProjectVo> list = new ArrayList<ListAssistCalculateProjectVo>( source.size() );
        for ( AssistCalculateProject assistCalculateProject : source ) {
            list.add( assistCalculateProjectToListAssistCalculateProjectVo( assistCalculateProject ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateProjectVo toGetAssistCalculateProjectVo(AssistCalculateProject source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateProjectVo getAssistCalculateProjectVo = new GetAssistCalculateProjectVo();

        getAssistCalculateProjectVo.setId( source.getId() );
        getAssistCalculateProjectVo.setResponsibleDepartment( source.getResponsibleDepartment() );
        getAssistCalculateProjectVo.setResponsiblePerson( source.getResponsiblePerson() );
        getAssistCalculateProjectVo.setPhone( source.getPhone() );
        getAssistCalculateProjectVo.setStartDate( source.getStartDate() );
        getAssistCalculateProjectVo.setEndDate( source.getEndDate() );

        return getAssistCalculateProjectVo;
    }

    @Override
    public AssistCalculateSupplier toAssistCalculateSupplier(CreateAssistCalculateSupplierForm source) {
        if ( source == null ) {
            return null;
        }

        AssistCalculateSupplier assistCalculateSupplier = new AssistCalculateSupplier();

        assistCalculateSupplier.setSupplierCate( source.getSupplierCate() );
        assistCalculateSupplier.setAddress( source.getAddress() );
        assistCalculateSupplier.setContacts( source.getContacts() );
        assistCalculateSupplier.setPhone( source.getPhone() );
        assistCalculateSupplier.setDisable( source.getDisable() );
        assistCalculateSupplier.setUnifiedSocialCreditCode( source.getUnifiedSocialCreditCode() );
        assistCalculateSupplier.setProvinceCode( source.getProvinceCode() );
        assistCalculateSupplier.setCityCode( source.getCityCode() );
        assistCalculateSupplier.setCountyCode( source.getCountyCode() );
        assistCalculateSupplier.setAssistCalculateSummaryId( source.getAssistCalculateSummaryId() );

        return assistCalculateSupplier;
    }

    @Override
    public List<ListAssistCalculateSupplierVo> toListAssistCalculateSupplierVo(List<AssistCalculateSupplier> source) {
        if ( source == null ) {
            return null;
        }

        List<ListAssistCalculateSupplierVo> list = new ArrayList<ListAssistCalculateSupplierVo>( source.size() );
        for ( AssistCalculateSupplier assistCalculateSupplier : source ) {
            list.add( assistCalculateSupplierToListAssistCalculateSupplierVo( assistCalculateSupplier ) );
        }

        return list;
    }

    @Override
    public GetAssistCalculateSupplierVo toGetAssistCalculateSupplierVo(AssistCalculateSupplier source) {
        if ( source == null ) {
            return null;
        }

        GetAssistCalculateSupplierVo getAssistCalculateSupplierVo = new GetAssistCalculateSupplierVo();

        getAssistCalculateSupplierVo.setId( source.getId() );
        getAssistCalculateSupplierVo.setSupplierCate( source.getSupplierCate() );
        getAssistCalculateSupplierVo.setAddress( source.getAddress() );
        getAssistCalculateSupplierVo.setContacts( source.getContacts() );
        getAssistCalculateSupplierVo.setPhone( source.getPhone() );
        getAssistCalculateSupplierVo.setUnifiedSocialCreditCode( source.getUnifiedSocialCreditCode() );
        getAssistCalculateSupplierVo.setProvinceCode( source.getProvinceCode() );
        getAssistCalculateSupplierVo.setCityCode( source.getCityCode() );
        getAssistCalculateSupplierVo.setCountyCode( source.getCountyCode() );

        return getAssistCalculateSupplierVo;
    }

    @Override
    public Subject toSubject(CreateSubjectForm source) {
        if ( source == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setPid( source.getPid() );
        subject.setCode( source.getCode() );
        subject.setName( source.getName() );
        subject.setMnemonicCode( source.getMnemonicCode() );
        subject.setBalanceDirection( source.getBalanceDirection() );
        subject.setDisable( source.getDisable() );

        return subject;
    }

    @Override
    public GetSubjectVo toGetSubjectVo(Subject source) {
        if ( source == null ) {
            return null;
        }

        GetSubjectVo getSubjectVo = new GetSubjectVo();

        getSubjectVo.setId( source.getId() );
        getSubjectVo.setPid( source.getPid() );
        getSubjectVo.setSort( source.getSort() );
        getSubjectVo.setCode( source.getCode() );
        getSubjectVo.setName( source.getName() );
        getSubjectVo.setMnemonicCode( source.getMnemonicCode() );
        getSubjectVo.setBalanceDirection( source.getBalanceDirection() );
        getSubjectVo.setDisable( source.getDisable() );
        getSubjectVo.setSubjectCate( source.getSubjectCate() );
        getSubjectVo.setSubjectType( source.getSubjectType() );
        getSubjectVo.setUseCount( source.getUseCount() );

        return getSubjectVo;
    }

    @Override
    public SubjectCalculateConfigForm.NumberCalculateConfig toNumberCalculateConfig(SubjectCalculateConfigVo.NumberCalculateConfig source) {
        if ( source == null ) {
            return null;
        }

        SubjectCalculateConfigForm.NumberCalculateConfig numberCalculateConfig = new SubjectCalculateConfigForm.NumberCalculateConfig();

        numberCalculateConfig.setUnitOfMeasurement( source.getUnitOfMeasurement() );

        return numberCalculateConfig;
    }

    @Override
    public List<SubjectCalculateConfigForm.AssistCalculateConfig> toAssistCalculateConfig(List<SubjectCalculateConfigVo.AssistCalculateConfig> source) {
        if ( source == null ) {
            return null;
        }

        List<SubjectCalculateConfigForm.AssistCalculateConfig> list = new ArrayList<SubjectCalculateConfigForm.AssistCalculateConfig>( source.size() );
        for ( SubjectCalculateConfigVo.AssistCalculateConfig assistCalculateConfig : source ) {
            list.add( assistCalculateConfigToAssistCalculateConfig( assistCalculateConfig ) );
        }

        return list;
    }

    @Override
    public ListSubjectVo toListSubjectVo(Subject source) {
        if ( source == null ) {
            return null;
        }

        ListSubjectVo listSubjectVo = new ListSubjectVo();

        listSubjectVo.setId( source.getId() );
        if ( source.getPid() != null ) {
            listSubjectVo.setPid( source.getPid().intValue() );
        }
        listSubjectVo.setSort( source.getSort() );
        listSubjectVo.setCode( source.getCode() );
        listSubjectVo.setName( source.getName() );
        listSubjectVo.setMnemonicCode( source.getMnemonicCode() );
        listSubjectVo.setDisable( source.getDisable() );
        listSubjectVo.setLevel( source.getLevel() );
        if ( source.getSubjectType() != null ) {
            listSubjectVo.setSubjectType( source.getSubjectType().byteValue() );
        }
        listSubjectVo.setNodeDepth( source.getNodeDepth() );
        listSubjectVo.setUseCount( source.getUseCount() );
        listSubjectVo.setParentSubjectDisable( source.getParentSubjectDisable() );

        return listSubjectVo;
    }

    @Override
    public GetSubjectDetailVo toGetSubjectDetailVo(Subject source) {
        if ( source == null ) {
            return null;
        }

        GetSubjectDetailVo getSubjectDetailVo = new GetSubjectDetailVo();

        getSubjectDetailVo.setId( source.getId() );
        getSubjectDetailVo.setPid( source.getPid() );
        getSubjectDetailVo.setCode( source.getCode() );
        getSubjectDetailVo.setName( source.getName() );
        getSubjectDetailVo.setSubjectCate( source.getSubjectCate() );

        return getSubjectDetailVo;
    }

    @Override
    public List<GetSubjectDetailVo.ForeignCurrencyConfigVo> toForeignCurrencyConfigVo(List<CurrencyConfig> source) {
        if ( source == null ) {
            return null;
        }

        List<GetSubjectDetailVo.ForeignCurrencyConfigVo> list = new ArrayList<GetSubjectDetailVo.ForeignCurrencyConfigVo>( source.size() );
        for ( CurrencyConfig currencyConfig : source ) {
            list.add( currencyConfigToForeignCurrencyConfigVo( currencyConfig ) );
        }

        return list;
    }

    @Override
    public List<ListSubjectByCateAndCodeAndNameVo> toListSubjectByCateAndCodeAndNameVo(List<Subject> source) {
        if ( source == null ) {
            return null;
        }

        List<ListSubjectByCateAndCodeAndNameVo> list = new ArrayList<ListSubjectByCateAndCodeAndNameVo>( source.size() );
        for ( Subject subject : source ) {
            list.add( subjectToListSubjectByCateAndCodeAndNameVo( subject ) );
        }

        return list;
    }

    @Override
    public List<GetSubjectDetailVo.AssistCalculateConfigVo> toAssistCalculateConfigVo(List<AssistCalculateCate> source) {
        if ( source == null ) {
            return null;
        }

        List<GetSubjectDetailVo.AssistCalculateConfigVo> list = new ArrayList<GetSubjectDetailVo.AssistCalculateConfigVo>( source.size() );
        for ( AssistCalculateCate assistCalculateCate : source ) {
            list.add( assistCalculateCateToAssistCalculateConfigVo( assistCalculateCate ) );
        }

        return list;
    }

    @Override
    public DownloadSubjectVo toDownloadSubjectVo(Subject source) {
        if ( source == null ) {
            return null;
        }

        DownloadSubjectVo downloadSubjectVo = new DownloadSubjectVo();

        downloadSubjectVo.setId( source.getId() );
        downloadSubjectVo.setCode( source.getCode() );
        downloadSubjectVo.setName( source.getName() );

        return downloadSubjectVo;
    }

    @Override
    public Voucher toVoucher(CreateVoucherForm source) {
        if ( source == null ) {
            return null;
        }

        Voucher voucher = new Voucher();

        voucher.setId( source.getId() );
        voucher.setVoucherWordConfigId( source.getVoucherWordConfigId() );
        voucher.setVoucherNumber( source.getVoucherNumber() );
        voucher.setVoucherDate( source.getVoucherDate() );
        voucher.setDocumentNum( source.getDocumentNum() );
        voucher.setTotalAmount( source.getTotalAmount() );
        voucher.setNotes( source.getNotes() );

        return voucher;
    }

    @Override
    public List<VoucherSubjectDetail> toVoucherSubjectDetail(List<CreateVoucherForm.VoucherSubjectDetailForm> source) {
        if ( source == null ) {
            return null;
        }

        List<VoucherSubjectDetail> list = new ArrayList<VoucherSubjectDetail>( source.size() );
        for ( CreateVoucherForm.VoucherSubjectDetailForm voucherSubjectDetailForm : source ) {
            list.add( voucherSubjectDetailFormToVoucherSubjectDetail( voucherSubjectDetailForm ) );
        }

        return list;
    }

    @Override
    public List<VoucherSubjectAssistCalculateDetail> toVoucherSubjectAssistCalculateDetail(List<CreateVoucherForm.VoucherSubjectAssistCalculateDetailForm> source) {
        if ( source == null ) {
            return null;
        }

        List<VoucherSubjectAssistCalculateDetail> list = new ArrayList<VoucherSubjectAssistCalculateDetail>( source.size() );
        for ( CreateVoucherForm.VoucherSubjectAssistCalculateDetailForm voucherSubjectAssistCalculateDetailForm : source ) {
            list.add( voucherSubjectAssistCalculateDetailFormToVoucherSubjectAssistCalculateDetail( voucherSubjectAssistCalculateDetailForm ) );
        }

        return list;
    }

    @Override
    public ListVoucherVo toListVoucherVo(VoucherDocuemt source) {
        if ( source == null ) {
            return null;
        }

        ListVoucherVo listVoucherVo = new ListVoucherVo();

        listVoucherVo.setVoucherSubjectDetailVoList( subjectDocumentListToVoucherSubjectDetailVoList( source.getSubjectDocuments() ) );
        listVoucherVo.setId( source.getId() );
        listVoucherVo.setVoucherWordConfigId( source.getVoucherWordConfigId() );
        listVoucherVo.setVoucherWord( source.getVoucherWord() );
        listVoucherVo.setVoucherNumber( source.getVoucherNumber() );
        listVoucherVo.setVoucherDate( source.getVoucherDate() );
        listVoucherVo.setDocumentNum( source.getDocumentNum() );
        listVoucherVo.setTotalAmount( source.getTotalAmount() );
        listVoucherVo.setNotes( source.getNotes() );

        return listVoucherVo;
    }

    @Override
    public PageInfo<ListVoucherVo> toListVoucherVo(PageInfo<VoucherDocuemt> source) {
        if ( source == null ) {
            return null;
        }

        PageInfo<ListVoucherVo> pageInfo = new PageInfo<ListVoucherVo>();

        pageInfo.setPageNum( source.getPageNum() );
        pageInfo.setPageSize( source.getPageSize() );
        pageInfo.setTotal( source.getTotal() );
        pageInfo.setPages( source.getPages() );
        pageInfo.setList( voucherDocuemtListToListVoucherVoList( source.getList() ) );

        return pageInfo;
    }

    @Override
    public GetVoucherVo toGetVoucherVo(Voucher source) {
        if ( source == null ) {
            return null;
        }

        GetVoucherVo getVoucherVo = new GetVoucherVo();

        getVoucherVo.setId( source.getId() );
        getVoucherVo.setVoucherWordConfigId( source.getVoucherWordConfigId() );
        getVoucherVo.setVoucherNumber( source.getVoucherNumber() );
        getVoucherVo.setVoucherDate( source.getVoucherDate() );
        getVoucherVo.setDocumentNum( source.getDocumentNum() );
        getVoucherVo.setTotalAmount( source.getTotalAmount() );
        getVoucherVo.setNotes( source.getNotes() );

        return getVoucherVo;
    }

    @Override
    public List<GetVoucherVo.VoucherSubjectDetailVo> toGetVoucherSubjectDetailVo(List<VoucherSubjectDetail> source) {
        if ( source == null ) {
            return null;
        }

        List<GetVoucherVo.VoucherSubjectDetailVo> list = new ArrayList<GetVoucherVo.VoucherSubjectDetailVo>( source.size() );
        for ( VoucherSubjectDetail voucherSubjectDetail : source ) {
            list.add( voucherSubjectDetailToVoucherSubjectDetailVo( voucherSubjectDetail ) );
        }

        return list;
    }

    @Override
    public List<GetVoucherVo.ForeignCurrencyConfigVo> toForeignCurrencyConfigVo2(List<CurrencyConfig> source) {
        if ( source == null ) {
            return null;
        }

        List<GetVoucherVo.ForeignCurrencyConfigVo> list = new ArrayList<GetVoucherVo.ForeignCurrencyConfigVo>( source.size() );
        for ( CurrencyConfig currencyConfig : source ) {
            list.add( currencyConfigToForeignCurrencyConfigVo1( currencyConfig ) );
        }

        return list;
    }

    @Override
    public VoucherDocuemt toVoucherES(Voucher source) {
        if ( source == null ) {
            return null;
        }

        VoucherDocuemt voucherDocuemt = new VoucherDocuemt();

        voucherDocuemt.setId( source.getId() );
        voucherDocuemt.setVoucherWordConfigId( source.getVoucherWordConfigId() );
        voucherDocuemt.setVoucherNumber( source.getVoucherNumber() );
        voucherDocuemt.setVoucherDate( source.getVoucherDate() );
        voucherDocuemt.setDocumentNum( source.getDocumentNum() );
        voucherDocuemt.setTotalAmount( source.getTotalAmount() );
        voucherDocuemt.setNotes( source.getNotes() );
        voucherDocuemt.setMemberId( source.getMemberId() );
        voucherDocuemt.setTenantId( source.getTenantId() );

        return voucherDocuemt;
    }

    @Override
    public List<VoucherDocuemt.SubjectDocument> toSubjectES(List<VoucherSubjectDetail> source) {
        if ( source == null ) {
            return null;
        }

        List<VoucherDocuemt.SubjectDocument> list = new ArrayList<VoucherDocuemt.SubjectDocument>( source.size() );
        for ( VoucherSubjectDetail voucherSubjectDetail : source ) {
            list.add( voucherSubjectDetailToSubjectDocument( voucherSubjectDetail ) );
        }

        return list;
    }

    @Override
    public List<VoucherDocuemt.AssistCalculateDocument> toAssistCalculateES(List<VoucherSubjectAssistCalculateDetail> source) {
        if ( source == null ) {
            return null;
        }

        List<VoucherDocuemt.AssistCalculateDocument> list = new ArrayList<VoucherDocuemt.AssistCalculateDocument>( source.size() );
        for ( VoucherSubjectAssistCalculateDetail voucherSubjectAssistCalculateDetail : source ) {
            list.add( voucherSubjectAssistCalculateDetailToAssistCalculateDocument( voucherSubjectAssistCalculateDetail ) );
        }

        return list;
    }

    @Override
    public List<ListMemberVo> toListMemberVo(List<Member> source) {
        if ( source == null ) {
            return null;
        }

        List<ListMemberVo> list = new ArrayList<ListMemberVo>( source.size() );
        for ( Member member : source ) {
            list.add( memberToListMemberVo( member ) );
        }

        return list;
    }

    protected ListSysResourceVo sysResourceToListSysResourceVo(SysResource sysResource) {
        if ( sysResource == null ) {
            return null;
        }

        ListSysResourceVo listSysResourceVo = new ListSysResourceVo();

        listSysResourceVo.setId( sysResource.getId() );
        listSysResourceVo.setName( sysResource.getName() );
        listSysResourceVo.setPid( sysResource.getPid() );
        listSysResourceVo.setPath( sysResource.getPath() );

        return listSysResourceVo;
    }

    protected ListRoleVo sysRoleToListRoleVo(SysRole sysRole) {
        if ( sysRole == null ) {
            return null;
        }

        ListRoleVo listRoleVo = new ListRoleVo();

        listRoleVo.setId( sysRole.getId() );
        listRoleVo.setRoleName( sysRole.getRoleName() );
        listRoleVo.setDisable( sysRole.getDisable() );

        return listRoleVo;
    }

    protected List<ListRoleVo> sysRoleListToListRoleVoList(List<SysRole> list) {
        if ( list == null ) {
            return null;
        }

        List<ListRoleVo> list1 = new ArrayList<ListRoleVo>( list.size() );
        for ( SysRole sysRole : list ) {
            list1.add( sysRoleToListRoleVo( sysRole ) );
        }

        return list1;
    }

    protected List<ListAccountBookVo> accountBookListToListAccountBookVoList(List<AccountBook> list) {
        if ( list == null ) {
            return null;
        }

        List<ListAccountBookVo> list1 = new ArrayList<ListAccountBookVo>( list.size() );
        for ( AccountBook accountBook : list ) {
            list1.add( toListAccountBookVo( accountBook ) );
        }

        return list1;
    }

    protected DataDictionaryVo dataDictionaryToDataDictionaryVo(DataDictionary dataDictionary) {
        if ( dataDictionary == null ) {
            return null;
        }

        DataDictionaryVo dataDictionaryVo = new DataDictionaryVo();

        dataDictionaryVo.setDataCode( dataDictionary.getDataCode() );
        dataDictionaryVo.setDataValue( dataDictionary.getDataValue() );

        return dataDictionaryVo;
    }

    protected ListCurrencyConfigVo currencyConfigToListCurrencyConfigVo(CurrencyConfig currencyConfig) {
        if ( currencyConfig == null ) {
            return null;
        }

        ListCurrencyConfigVo listCurrencyConfigVo = new ListCurrencyConfigVo();

        listCurrencyConfigVo.setId( currencyConfig.getId() );
        listCurrencyConfigVo.setCode( currencyConfig.getCode() );
        listCurrencyConfigVo.setName( currencyConfig.getName() );
        listCurrencyConfigVo.setExchangeRate( currencyConfig.getExchangeRate() );
        listCurrencyConfigVo.setBaseCurrencyFlag( currencyConfig.getBaseCurrencyFlag() );
        listCurrencyConfigVo.setUseCount( currencyConfig.getUseCount() );

        return listCurrencyConfigVo;
    }

    protected ListVoucherWordConfigVo voucherWordConfigToListVoucherWordConfigVo(VoucherWordConfig voucherWordConfig) {
        if ( voucherWordConfig == null ) {
            return null;
        }

        ListVoucherWordConfigVo listVoucherWordConfigVo = new ListVoucherWordConfigVo();

        if ( voucherWordConfig.getId() != null ) {
            listVoucherWordConfigVo.setId( voucherWordConfig.getId().intValue() );
        }
        listVoucherWordConfigVo.setVoucherWord( voucherWordConfig.getVoucherWord() );
        listVoucherWordConfigVo.setPrintTitle( voucherWordConfig.getPrintTitle() );
        listVoucherWordConfigVo.setDefaultFlag( voucherWordConfig.getDefaultFlag() );
        listVoucherWordConfigVo.setUseCount( voucherWordConfig.getUseCount() );

        return listVoucherWordConfigVo;
    }

    protected ListCalculateCateVo assistCalculateCateToListCalculateCateVo(AssistCalculateCate assistCalculateCate) {
        if ( assistCalculateCate == null ) {
            return null;
        }

        ListCalculateCateVo listCalculateCateVo = new ListCalculateCateVo();

        if ( assistCalculateCate.getId() != null ) {
            listCalculateCateVo.setId( assistCalculateCate.getId().intValue() );
        }
        listCalculateCateVo.setName( assistCalculateCate.getName() );
        listCalculateCateVo.setLevel( assistCalculateCate.getLevel() );
        listCalculateCateVo.setCode( assistCalculateCate.getCode() );

        return listCalculateCateVo;
    }

    protected ListAssistCalculateSummaryVo assistCalculateSummaryToListAssistCalculateSummaryVo(AssistCalculateSummary assistCalculateSummary) {
        if ( assistCalculateSummary == null ) {
            return null;
        }

        ListAssistCalculateSummaryVo listAssistCalculateSummaryVo = new ListAssistCalculateSummaryVo();

        listAssistCalculateSummaryVo.setId( assistCalculateSummary.getId() );
        listAssistCalculateSummaryVo.setName( assistCalculateSummary.getName() );
        listAssistCalculateSummaryVo.setCode( assistCalculateSummary.getCode() );
        listAssistCalculateSummaryVo.setMnemonicCode( assistCalculateSummary.getMnemonicCode() );

        return listAssistCalculateSummaryVo;
    }

    protected List<ListAssistCalculateSummaryVo> assistCalculateSummaryListToListAssistCalculateSummaryVoList(List<AssistCalculateSummary> list) {
        if ( list == null ) {
            return null;
        }

        List<ListAssistCalculateSummaryVo> list1 = new ArrayList<ListAssistCalculateSummaryVo>( list.size() );
        for ( AssistCalculateSummary assistCalculateSummary : list ) {
            list1.add( assistCalculateSummaryToListAssistCalculateSummaryVo( assistCalculateSummary ) );
        }

        return list1;
    }

    protected ListAssistCalculateCashFlowVo assistCalculateCashFlowToListAssistCalculateCashFlowVo(AssistCalculateCashFlow assistCalculateCashFlow) {
        if ( assistCalculateCashFlow == null ) {
            return null;
        }

        ListAssistCalculateCashFlowVo listAssistCalculateCashFlowVo = new ListAssistCalculateCashFlowVo();

        listAssistCalculateCashFlowVo.setId( assistCalculateCashFlow.getId() );
        listAssistCalculateCashFlowVo.setNotes( assistCalculateCashFlow.getNotes() );
        listAssistCalculateCashFlowVo.setDisable( assistCalculateCashFlow.getDisable() );
        listAssistCalculateCashFlowVo.setAssistCalculateSummaryId( assistCalculateCashFlow.getAssistCalculateSummaryId() );
        listAssistCalculateCashFlowVo.setCashFlowCate( assistCalculateCashFlow.getCashFlowCate() );

        return listAssistCalculateCashFlowVo;
    }

    protected ListAssistCalculateCustomerVo assistCalculateCustomerToListAssistCalculateCustomerVo(AssistCalculateCustomer assistCalculateCustomer) {
        if ( assistCalculateCustomer == null ) {
            return null;
        }

        ListAssistCalculateCustomerVo listAssistCalculateCustomerVo = new ListAssistCalculateCustomerVo();

        listAssistCalculateCustomerVo.setId( assistCalculateCustomer.getId() );
        listAssistCalculateCustomerVo.setDisable( assistCalculateCustomer.getDisable() );
        listAssistCalculateCustomerVo.setAssistCalculateSummaryId( assistCalculateCustomer.getAssistCalculateSummaryId() );
        listAssistCalculateCustomerVo.setCustomerCate( assistCalculateCustomer.getCustomerCate() );
        listAssistCalculateCustomerVo.setAddress( assistCalculateCustomer.getAddress() );
        listAssistCalculateCustomerVo.setContacts( assistCalculateCustomer.getContacts() );
        listAssistCalculateCustomerVo.setPhone( assistCalculateCustomer.getPhone() );
        listAssistCalculateCustomerVo.setUnifiedSocialCreditCode( assistCalculateCustomer.getUnifiedSocialCreditCode() );

        return listAssistCalculateCustomerVo;
    }

    protected ListAssistCalculateCustomVo assistCalculateCustomToListAssistCalculateCustomVo(AssistCalculateCustom assistCalculateCustom) {
        if ( assistCalculateCustom == null ) {
            return null;
        }

        ListAssistCalculateCustomVo listAssistCalculateCustomVo = new ListAssistCalculateCustomVo();

        listAssistCalculateCustomVo.setId( assistCalculateCustom.getId() );
        listAssistCalculateCustomVo.setDisable( assistCalculateCustom.getDisable() );
        listAssistCalculateCustomVo.setAssistCalculateSummaryId( assistCalculateCustom.getAssistCalculateSummaryId() );
        listAssistCalculateCustomVo.setC1( assistCalculateCustom.getC1() );
        listAssistCalculateCustomVo.setC2( assistCalculateCustom.getC2() );
        listAssistCalculateCustomVo.setC3( assistCalculateCustom.getC3() );
        listAssistCalculateCustomVo.setC4( assistCalculateCustom.getC4() );
        listAssistCalculateCustomVo.setC5( assistCalculateCustom.getC5() );
        listAssistCalculateCustomVo.setC6( assistCalculateCustom.getC6() );
        listAssistCalculateCustomVo.setC7( assistCalculateCustom.getC7() );
        listAssistCalculateCustomVo.setC8( assistCalculateCustom.getC8() );
        listAssistCalculateCustomVo.setC9( assistCalculateCustom.getC9() );
        listAssistCalculateCustomVo.setC10( assistCalculateCustom.getC10() );

        return listAssistCalculateCustomVo;
    }

    protected ListAssistCalculateDepartmentVo assistCalculateDepartmentToListAssistCalculateDepartmentVo(AssistCalculateDepartment assistCalculateDepartment) {
        if ( assistCalculateDepartment == null ) {
            return null;
        }

        ListAssistCalculateDepartmentVo listAssistCalculateDepartmentVo = new ListAssistCalculateDepartmentVo();

        listAssistCalculateDepartmentVo.setId( assistCalculateDepartment.getId() );
        listAssistCalculateDepartmentVo.setDisable( assistCalculateDepartment.getDisable() );
        listAssistCalculateDepartmentVo.setAssistCalculateSummaryId( assistCalculateDepartment.getAssistCalculateSummaryId() );
        listAssistCalculateDepartmentVo.setManager( assistCalculateDepartment.getManager() );
        listAssistCalculateDepartmentVo.setPhone( assistCalculateDepartment.getPhone() );
        listAssistCalculateDepartmentVo.setStartDate( assistCalculateDepartment.getStartDate() );
        listAssistCalculateDepartmentVo.setRevokeDate( assistCalculateDepartment.getRevokeDate() );

        return listAssistCalculateDepartmentVo;
    }

    protected ListAssistCalculateEmployeeVo assistCalculateEmployeeToListAssistCalculateEmployeeVo(AssistCalculateEmployee assistCalculateEmployee) {
        if ( assistCalculateEmployee == null ) {
            return null;
        }

        ListAssistCalculateEmployeeVo listAssistCalculateEmployeeVo = new ListAssistCalculateEmployeeVo();

        listAssistCalculateEmployeeVo.setId( assistCalculateEmployee.getId() );
        listAssistCalculateEmployeeVo.setDisable( assistCalculateEmployee.getDisable() );
        listAssistCalculateEmployeeVo.setAssistCalculateSummaryId( assistCalculateEmployee.getAssistCalculateSummaryId() );
        listAssistCalculateEmployeeVo.setSex( assistCalculateEmployee.getSex() );
        listAssistCalculateEmployeeVo.setDepartmentCode( assistCalculateEmployee.getDepartmentCode() );
        listAssistCalculateEmployeeVo.setDepartmentName( assistCalculateEmployee.getDepartmentName() );
        listAssistCalculateEmployeeVo.setPosition( assistCalculateEmployee.getPosition() );
        listAssistCalculateEmployeeVo.setJob( assistCalculateEmployee.getJob() );
        listAssistCalculateEmployeeVo.setPhone( assistCalculateEmployee.getPhone() );
        listAssistCalculateEmployeeVo.setBirthday( assistCalculateEmployee.getBirthday() );
        listAssistCalculateEmployeeVo.setStartDate( assistCalculateEmployee.getStartDate() );
        listAssistCalculateEmployeeVo.setDepartureDate( assistCalculateEmployee.getDepartureDate() );

        return listAssistCalculateEmployeeVo;
    }

    protected ListAssistCalculateInventoryVo assistCalculateInventoryToListAssistCalculateInventoryVo(AssistCalculateInventory assistCalculateInventory) {
        if ( assistCalculateInventory == null ) {
            return null;
        }

        ListAssistCalculateInventoryVo listAssistCalculateInventoryVo = new ListAssistCalculateInventoryVo();

        listAssistCalculateInventoryVo.setId( assistCalculateInventory.getId() );
        listAssistCalculateInventoryVo.setDisable( assistCalculateInventory.getDisable() );
        listAssistCalculateInventoryVo.setAssistCalculateSummaryId( assistCalculateInventory.getAssistCalculateSummaryId() );
        listAssistCalculateInventoryVo.setSpecifications( assistCalculateInventory.getSpecifications() );
        listAssistCalculateInventoryVo.setInventoryCate( assistCalculateInventory.getInventoryCate() );
        listAssistCalculateInventoryVo.setUnits( assistCalculateInventory.getUnits() );
        listAssistCalculateInventoryVo.setStartDate( assistCalculateInventory.getStartDate() );
        listAssistCalculateInventoryVo.setEndDate( assistCalculateInventory.getEndDate() );

        return listAssistCalculateInventoryVo;
    }

    protected ListAssistCalculateProjectVo assistCalculateProjectToListAssistCalculateProjectVo(AssistCalculateProject assistCalculateProject) {
        if ( assistCalculateProject == null ) {
            return null;
        }

        ListAssistCalculateProjectVo listAssistCalculateProjectVo = new ListAssistCalculateProjectVo();

        listAssistCalculateProjectVo.setId( assistCalculateProject.getId() );
        listAssistCalculateProjectVo.setDisable( assistCalculateProject.getDisable() );
        listAssistCalculateProjectVo.setAssistCalculateSummaryId( assistCalculateProject.getAssistCalculateSummaryId() );
        listAssistCalculateProjectVo.setResponsibleDepartment( assistCalculateProject.getResponsibleDepartment() );
        listAssistCalculateProjectVo.setResponsiblePerson( assistCalculateProject.getResponsiblePerson() );
        listAssistCalculateProjectVo.setPhone( assistCalculateProject.getPhone() );
        listAssistCalculateProjectVo.setStartDate( assistCalculateProject.getStartDate() );
        listAssistCalculateProjectVo.setEndDate( assistCalculateProject.getEndDate() );

        return listAssistCalculateProjectVo;
    }

    protected ListAssistCalculateSupplierVo assistCalculateSupplierToListAssistCalculateSupplierVo(AssistCalculateSupplier assistCalculateSupplier) {
        if ( assistCalculateSupplier == null ) {
            return null;
        }

        ListAssistCalculateSupplierVo listAssistCalculateSupplierVo = new ListAssistCalculateSupplierVo();

        listAssistCalculateSupplierVo.setId( assistCalculateSupplier.getId() );
        listAssistCalculateSupplierVo.setDisable( assistCalculateSupplier.getDisable() );
        listAssistCalculateSupplierVo.setAssistCalculateSummaryId( assistCalculateSupplier.getAssistCalculateSummaryId() );
        listAssistCalculateSupplierVo.setSupplierCate( assistCalculateSupplier.getSupplierCate() );
        listAssistCalculateSupplierVo.setAddress( assistCalculateSupplier.getAddress() );
        listAssistCalculateSupplierVo.setContacts( assistCalculateSupplier.getContacts() );
        listAssistCalculateSupplierVo.setPhone( assistCalculateSupplier.getPhone() );
        listAssistCalculateSupplierVo.setUnifiedSocialCreditCode( assistCalculateSupplier.getUnifiedSocialCreditCode() );

        return listAssistCalculateSupplierVo;
    }

    protected SubjectCalculateConfigForm.AssistCalculateConfig assistCalculateConfigToAssistCalculateConfig(SubjectCalculateConfigVo.AssistCalculateConfig assistCalculateConfig) {
        if ( assistCalculateConfig == null ) {
            return null;
        }

        SubjectCalculateConfigForm.AssistCalculateConfig assistCalculateConfig1 = new SubjectCalculateConfigForm.AssistCalculateConfig();

        assistCalculateConfig1.setAssistCalculateId( assistCalculateConfig.getAssistCalculateId() );
        assistCalculateConfig1.setRequiredFlag( assistCalculateConfig.getRequiredFlag() );

        return assistCalculateConfig1;
    }

    protected GetSubjectDetailVo.ForeignCurrencyConfigVo currencyConfigToForeignCurrencyConfigVo(CurrencyConfig currencyConfig) {
        if ( currencyConfig == null ) {
            return null;
        }

        GetSubjectDetailVo.ForeignCurrencyConfigVo foreignCurrencyConfigVo = new GetSubjectDetailVo.ForeignCurrencyConfigVo();

        foreignCurrencyConfigVo.setId( currencyConfig.getId() );
        foreignCurrencyConfigVo.setName( currencyConfig.getName() );
        foreignCurrencyConfigVo.setExchangeRate( currencyConfig.getExchangeRate() );
        foreignCurrencyConfigVo.setBaseCurrencyFlag( currencyConfig.getBaseCurrencyFlag() );

        return foreignCurrencyConfigVo;
    }

    protected ListSubjectByCateAndCodeAndNameVo subjectToListSubjectByCateAndCodeAndNameVo(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        ListSubjectByCateAndCodeAndNameVo listSubjectByCateAndCodeAndNameVo = new ListSubjectByCateAndCodeAndNameVo();

        listSubjectByCateAndCodeAndNameVo.setId( subject.getId() );
        listSubjectByCateAndCodeAndNameVo.setPid( subject.getPid() );
        if ( subject.getSubjectCate() != null ) {
            listSubjectByCateAndCodeAndNameVo.setSubjectCate( String.valueOf( subject.getSubjectCate() ) );
        }
        listSubjectByCateAndCodeAndNameVo.setCode( subject.getCode() );
        listSubjectByCateAndCodeAndNameVo.setName( subject.getName() );

        return listSubjectByCateAndCodeAndNameVo;
    }

    protected GetSubjectDetailVo.AssistCalculateConfigVo assistCalculateCateToAssistCalculateConfigVo(AssistCalculateCate assistCalculateCate) {
        if ( assistCalculateCate == null ) {
            return null;
        }

        GetSubjectDetailVo.AssistCalculateConfigVo assistCalculateConfigVo = new GetSubjectDetailVo.AssistCalculateConfigVo();

        assistCalculateConfigVo.setId( assistCalculateCate.getId() );
        assistCalculateConfigVo.setName( assistCalculateCate.getName() );
        assistCalculateConfigVo.setCode( assistCalculateCate.getCode() );

        return assistCalculateConfigVo;
    }

    protected VoucherSubjectDetail voucherSubjectDetailFormToVoucherSubjectDetail(CreateVoucherForm.VoucherSubjectDetailForm voucherSubjectDetailForm) {
        if ( voucherSubjectDetailForm == null ) {
            return null;
        }

        VoucherSubjectDetail voucherSubjectDetail = new VoucherSubjectDetail();

        voucherSubjectDetail.setSubjectId( voucherSubjectDetailForm.getSubjectId() );
        voucherSubjectDetail.setCurrencyConfigId( voucherSubjectDetailForm.getCurrencyConfigId() );
        voucherSubjectDetail.setExchangeRate( voucherSubjectDetailForm.getExchangeRate() );
        voucherSubjectDetail.setOriginalCurrency( voucherSubjectDetailForm.getOriginalCurrency() );
        voucherSubjectDetail.setSubjectNum( voucherSubjectDetailForm.getSubjectNum() );
        voucherSubjectDetail.setSubjectUnitPrice( voucherSubjectDetailForm.getSubjectUnitPrice() );
        voucherSubjectDetail.setBalance( voucherSubjectDetailForm.getBalance() );
        voucherSubjectDetail.setDebitAmount( voucherSubjectDetailForm.getDebitAmount() );
        voucherSubjectDetail.setCreditAmount( voucherSubjectDetailForm.getCreditAmount() );
        voucherSubjectDetail.setSummary( voucherSubjectDetailForm.getSummary() );
        voucherSubjectDetail.setRowNo( voucherSubjectDetailForm.getRowNo() );

        return voucherSubjectDetail;
    }

    protected VoucherSubjectAssistCalculateDetail voucherSubjectAssistCalculateDetailFormToVoucherSubjectAssistCalculateDetail(CreateVoucherForm.VoucherSubjectAssistCalculateDetailForm voucherSubjectAssistCalculateDetailForm) {
        if ( voucherSubjectAssistCalculateDetailForm == null ) {
            return null;
        }

        VoucherSubjectAssistCalculateDetail voucherSubjectAssistCalculateDetail = new VoucherSubjectAssistCalculateDetail();

        voucherSubjectAssistCalculateDetail.setSubjectId( voucherSubjectAssistCalculateDetailForm.getSubjectId() );
        voucherSubjectAssistCalculateDetail.setAssistCalculateCateId( voucherSubjectAssistCalculateDetailForm.getAssistCalculateCateId() );
        voucherSubjectAssistCalculateDetail.setAssistCalculateId( voucherSubjectAssistCalculateDetailForm.getAssistCalculateId() );
        voucherSubjectAssistCalculateDetail.setRowNo( voucherSubjectAssistCalculateDetailForm.getRowNo() );

        return voucherSubjectAssistCalculateDetail;
    }

    protected ListVoucherVo.VoucherSubjectDetailVo subjectDocumentToVoucherSubjectDetailVo(VoucherDocuemt.SubjectDocument subjectDocument) {
        if ( subjectDocument == null ) {
            return null;
        }

        ListVoucherVo.VoucherSubjectDetailVo voucherSubjectDetailVo = new ListVoucherVo.VoucherSubjectDetailVo();

        voucherSubjectDetailVo.setId( subjectDocument.getId() );
        voucherSubjectDetailVo.setVoucherId( subjectDocument.getVoucherId() );
        voucherSubjectDetailVo.setSummary( subjectDocument.getSummary() );
        voucherSubjectDetailVo.setSubjectId( subjectDocument.getSubjectId() );
        voucherSubjectDetailVo.setSubjectName( subjectDocument.getSubjectName() );
        voucherSubjectDetailVo.setSubjectFullName( subjectDocument.getSubjectFullName() );
        voucherSubjectDetailVo.setShowSubjectName( subjectDocument.getShowSubjectName() );
        voucherSubjectDetailVo.setCurrencyConfigId( subjectDocument.getCurrencyConfigId() );
        voucherSubjectDetailVo.setCurrencyConfigName( subjectDocument.getCurrencyConfigName() );
        voucherSubjectDetailVo.setExchangeRate( subjectDocument.getExchangeRate() );
        voucherSubjectDetailVo.setOriginalCurrency( subjectDocument.getOriginalCurrency() );
        voucherSubjectDetailVo.setSubjectNum( subjectDocument.getSubjectNum() );
        voucherSubjectDetailVo.setSubjectUnitPrice( subjectDocument.getSubjectUnitPrice() );
        voucherSubjectDetailVo.setBalance( subjectDocument.getBalance() );
        voucherSubjectDetailVo.setDebitAmount( subjectDocument.getDebitAmount() );
        voucherSubjectDetailVo.setCreditAmount( subjectDocument.getCreditAmount() );

        return voucherSubjectDetailVo;
    }

    protected List<ListVoucherVo.VoucherSubjectDetailVo> subjectDocumentListToVoucherSubjectDetailVoList(List<VoucherDocuemt.SubjectDocument> list) {
        if ( list == null ) {
            return null;
        }

        List<ListVoucherVo.VoucherSubjectDetailVo> list1 = new ArrayList<ListVoucherVo.VoucherSubjectDetailVo>( list.size() );
        for ( VoucherDocuemt.SubjectDocument subjectDocument : list ) {
            list1.add( subjectDocumentToVoucherSubjectDetailVo( subjectDocument ) );
        }

        return list1;
    }

    protected List<ListVoucherVo> voucherDocuemtListToListVoucherVoList(List<VoucherDocuemt> list) {
        if ( list == null ) {
            return null;
        }

        List<ListVoucherVo> list1 = new ArrayList<ListVoucherVo>( list.size() );
        for ( VoucherDocuemt voucherDocuemt : list ) {
            list1.add( toListVoucherVo( voucherDocuemt ) );
        }

        return list1;
    }

    protected GetVoucherVo.VoucherSubjectDetailVo voucherSubjectDetailToVoucherSubjectDetailVo(VoucherSubjectDetail voucherSubjectDetail) {
        if ( voucherSubjectDetail == null ) {
            return null;
        }

        GetVoucherVo.VoucherSubjectDetailVo voucherSubjectDetailVo = new GetVoucherVo.VoucherSubjectDetailVo();

        voucherSubjectDetailVo.setId( voucherSubjectDetail.getId() );
        voucherSubjectDetailVo.setVoucherId( voucherSubjectDetail.getVoucherId() );
        voucherSubjectDetailVo.setSummary( voucherSubjectDetail.getSummary() );
        voucherSubjectDetailVo.setSubjectId( voucherSubjectDetail.getSubjectId() );
        voucherSubjectDetailVo.setCurrencyConfigId( voucherSubjectDetail.getCurrencyConfigId() );
        voucherSubjectDetailVo.setExchangeRate( voucherSubjectDetail.getExchangeRate() );
        voucherSubjectDetailVo.setOriginalCurrency( voucherSubjectDetail.getOriginalCurrency() );
        voucherSubjectDetailVo.setSubjectNum( voucherSubjectDetail.getSubjectNum() );
        voucherSubjectDetailVo.setSubjectUnitPrice( voucherSubjectDetail.getSubjectUnitPrice() );
        voucherSubjectDetailVo.setBalance( voucherSubjectDetail.getBalance() );
        voucherSubjectDetailVo.setDebitAmount( voucherSubjectDetail.getDebitAmount() );
        voucherSubjectDetailVo.setCreditAmount( voucherSubjectDetail.getCreditAmount() );
        voucherSubjectDetailVo.setEnableNumberCalculateConfig( voucherSubjectDetail.getEnableNumberCalculateConfig() );
        voucherSubjectDetailVo.setEnableAssistCalculateConfigs( voucherSubjectDetail.getEnableAssistCalculateConfigs() );
        voucherSubjectDetailVo.setEnableForeignCurrencyConfig( voucherSubjectDetail.getEnableForeignCurrencyConfig() );

        return voucherSubjectDetailVo;
    }

    protected GetVoucherVo.ForeignCurrencyConfigVo currencyConfigToForeignCurrencyConfigVo1(CurrencyConfig currencyConfig) {
        if ( currencyConfig == null ) {
            return null;
        }

        GetVoucherVo.ForeignCurrencyConfigVo foreignCurrencyConfigVo = new GetVoucherVo.ForeignCurrencyConfigVo();

        foreignCurrencyConfigVo.setId( currencyConfig.getId() );
        foreignCurrencyConfigVo.setName( currencyConfig.getName() );
        foreignCurrencyConfigVo.setExchangeRate( currencyConfig.getExchangeRate() );
        foreignCurrencyConfigVo.setBaseCurrencyFlag( currencyConfig.getBaseCurrencyFlag() );

        return foreignCurrencyConfigVo;
    }

    protected VoucherDocuemt.SubjectDocument voucherSubjectDetailToSubjectDocument(VoucherSubjectDetail voucherSubjectDetail) {
        if ( voucherSubjectDetail == null ) {
            return null;
        }

        VoucherDocuemt.SubjectDocument subjectDocument = new VoucherDocuemt.SubjectDocument();

        subjectDocument.setId( voucherSubjectDetail.getId() );
        subjectDocument.setVoucherId( voucherSubjectDetail.getVoucherId() );
        subjectDocument.setSummary( voucherSubjectDetail.getSummary() );
        subjectDocument.setSubjectId( voucherSubjectDetail.getSubjectId() );
        subjectDocument.setCurrencyConfigId( voucherSubjectDetail.getCurrencyConfigId() );
        subjectDocument.setExchangeRate( voucherSubjectDetail.getExchangeRate() );
        subjectDocument.setOriginalCurrency( voucherSubjectDetail.getOriginalCurrency() );
        subjectDocument.setSubjectNum( voucherSubjectDetail.getSubjectNum() );
        subjectDocument.setSubjectUnitPrice( voucherSubjectDetail.getSubjectUnitPrice() );
        subjectDocument.setBalance( voucherSubjectDetail.getBalance() );
        subjectDocument.setDebitAmount( voucherSubjectDetail.getDebitAmount() );
        subjectDocument.setCreditAmount( voucherSubjectDetail.getCreditAmount() );

        return subjectDocument;
    }

    protected VoucherDocuemt.AssistCalculateDocument voucherSubjectAssistCalculateDetailToAssistCalculateDocument(VoucherSubjectAssistCalculateDetail voucherSubjectAssistCalculateDetail) {
        if ( voucherSubjectAssistCalculateDetail == null ) {
            return null;
        }

        VoucherDocuemt.AssistCalculateDocument assistCalculateDocument = new VoucherDocuemt.AssistCalculateDocument();

        assistCalculateDocument.setAssistCalculateId( voucherSubjectAssistCalculateDetail.getAssistCalculateId() );

        return assistCalculateDocument;
    }

    protected ListMemberVo memberToListMemberVo(Member member) {
        if ( member == null ) {
            return null;
        }

        ListMemberVo listMemberVo = new ListMemberVo();

        listMemberVo.setId( member.getId() );
        listMemberVo.setNickName( member.getNickName() );

        return listMemberVo;
    }
}
