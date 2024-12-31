import { ExclamationCircleOutlined, PlusOutlined, SearchOutlined } from '@ant-design/icons';
import { ActionType, PageContainer, ProColumns, ProFormDatePicker, ProFormSelect } from '@ant-design/pro-components';
import { ProTable, TableDropdown, ProFormText, ProForm } from '@ant-design/pro-components';
import { Button, Dropdown, Space, Switch, Modal, message, Tag, Select, DatePicker, Drawer, ConfigProvider } from 'antd';

import React, { useState, useEffect, useRef } from 'react';
import request from 'umi-request';
import { getBalanceSheet } from '@/services/ant-design-pro/report';

import { Link } from '@umijs/max';
import './index.less'

const { RangePicker } = DatePicker;
import type { TableRowSelection } from 'antd/es/table/interface';
import moment from 'moment';

export default () => {
    const [tenantId, setTenantId] = useState(Number);
    const [openEditTenant, setOpenEditTenant] = useState(false);
    const [editForm] = ProForm.useForm();
    const actionRef = useRef<ActionType>();
    // 获取当前系统时间，并精确到月份的第一天
    const defaultDate = moment().startOf('month');

    const onCloseTenant = async () => {
        setOpenEditTenant(false);
    }


    interface DataType {
        key: React.ReactNode;
        name: string;
        age: number;
        address: string;
        children?: DataType[];
    }
    const columns: ProColumns[] = [
        {
            title: '资产',
            dataIndex: 'asset',
            ellipsis: true,
            // tip: '标题过长会自动收缩',
            render: (text, record, _, action) => (
                record.assetFontBold ? <span style={{ fontWeight: 'bold' }}>{record.asset}</span> : <span style={{ marginLeft: record.assetTabCount * 20 }}> {record.asset}</span>
            ),
            search: false
        },
        {
            disable: true,
            title: '行次',
            dataIndex: 'assetRowNo',
            filters: true,
            ellipsis: true,
            width: 50,
            search: false,
            render: (text, record, _, action) => (
                record.assetRowNo ? record.assetRowNo : ""
            ),
        },
        {
            disable: true,
            title: '期末余额',
            dataIndex: 'assetTermEndBalance',
            filters: true,
            ellipsis: true,
            width: 100,
            search: false,
            render: (text, record, _, action) => record.assetTermEndBalance ? parseFloat(record.assetTermEndBalance).toFixed(2).toLocaleString() : "", // 保留两位小数并格式化数字
        },
        {
            disable: true,
            title: '年初余额',
            dataIndex: 'assetYarBeginBalance',
            filters: true,
            ellipsis: true,
            width: 100,
            search: false,
            render: (text, record, _, action) => record.assetYarBeginBalance ? parseFloat(record.assetYarBeginBalance).toFixed(2).toLocaleString() : "", // 保留两位小数并格式化数字
        },
        {
            disable: true,
            title: '负债和所有权益',
            dataIndex: 'liabilities',
            filters: true,
            ellipsis: true,
            search: false,
            render: (text, record, _, action) => (
                record.liabilitiesFontBold ? <span style={{ fontWeight: 'bold' }}>{record.liabilities}</span> : <span style={{ marginLeft: record.liabilitiesTabCount * 20 }} > {record.liabilities}</span>
            ),
        },
        {
            disable: true,
            title: '行次',
            dataIndex: 'liabilitiesRowNo',
            filters: true,
            ellipsis: true,
            width: 50,
            search: false,
            render: (text, record, _, action) => (
                record.liabilitiesRowNo ? record.liabilitiesRowNo : ""
            ),
        },
        {
            disable: true,
            title: '期末余额',
            dataIndex: 'liabilitiesTermEndBalance',
            filters: true,
            ellipsis: true,
            width: 100,
            search: false,
            render: (text, record, _, action) => record.liabilitiesTermEndBalance ? parseFloat(record.liabilitiesTermEndBalance).toFixed(2).toLocaleString() : "", // 保留两位小数并格式化数字
        },
        {
            disable: true,
            title: '年初余额',
            dataIndex: 'liabilitiesYarBeginBalance',
            filters: true,
            ellipsis: true,
            valueType: 'select',
            width: 100,
            search: false,
            render: (text, record, _, action) => record.liabilitiesYarBeginBalance ? parseFloat(record.liabilitiesYarBeginBalance).toFixed(2).toLocaleString() : "", // 保留两位小数并格式化数字
        }
    ];
    const onUpdateTenant = async (record: API.ListRoleVo) => {
        try {
            // 替换成实际的接口地址
            await update({ ...record }).then(response => {
                if (response.data) {
                    message.success("修改成功");
                }
                else {
                    message.error("修改失败");
                }
                onCloseTenant();
            }).catch(error => {
                console.log(error);
            });
        } catch (error) {
            console.error(error);
        }
        finally {
            if (actionRef.current) {
                actionRef.current.reload();
            }
        }
    }
    const rowClassName = (record, index) => {
        return index % 2 === 0 ? 'even-row' : 'odd-row';
    };
    return (
        <PageContainer>

            <ConfigProvider
                theme={{
                    components: {
                        Table: {
                            // footerColor: 'white',
                            // headerBorderRadius: 0,
                            // cellFontSize: 5,
                            // cellPaddingBlock: 5,
                            headerBg: '#bae0ff',
                            // borderColor: 'black',
                            rowHoverBg: '#bae0ff'
                        },
                    },
                }}
            >
                <ProTable<API.ListAccountBookVoItem, API.PageParams>
                    size={'small'}
                    search={false}
                    columns={columns}
                    actionRef={actionRef}
                    rowClassName={rowClassName}
                    cardBordered
                    bordered
                    request={async (params = {}, sort, filter) => {
                        let data = await getBalanceSheet({});
                        return { data: data.data.balanceRows };
                    }}
                    editable={{
                        type: 'multiple',
                    }}
                    columnsState={{
                        persistenceKey: 'pro-table-singe-demos',
                        persistenceType: 'localStorage',
                        onChange(value) {
                            console.log('value: ', value);
                        },
                    }}
                    rowKey="id"
                    options={{
                        setting: {
                            listsHeight: 400,
                        },
                    }}
                    dateFormatter="string"
                    headerTitle="资产负债表"
                    pagination={false}
                    onReset={() => {

                    }}
                    toolBarRender={() => [
                        <DatePicker defaultValue={defaultDate} picker="month" />,
                        <Button key="button" icon={<SearchOutlined />} type="primary" href='/FinanceSetting/VoucherWordConfig/Create'>
                            查看报表
                        </Button>
                    ]}
                />
            </ConfigProvider>
            <Drawer title="编辑租户" placement="right" onClose={onCloseTenant} open={openEditTenant}>
                <ProForm
                    form={editForm}
                    initialValues={{
                        hideInMenu: false,
                        layout: true
                    }}
                    layout='horizontal' // 设置为垂直布局
                    onFinish={onUpdateTenant}
                    labelCol={{ span: 6 }}   // 控制 label 的布局，可以调整 span 的值
                    wrapperCol={{ span: 14 }} // 控制包裹内容的布局，可以调整 span 的值
                    submitter={{
                        render: (_, dom) => (
                            <ProForm.Item
                                wrapperCol={{ offset: 6, span: 14 }}
                                colon={false}
                            >
                                <Button type="primary" onClick={() => editForm?.submit?.()}>
                                    确定
                                </Button>
                            </ProForm.Item>
                        ),
                    }}
                >
                    <ProFormText
                        width="md"
                        name="id"
                        label="ID"
                        disabled

                    />
                    <ProFormText
                        width="md"
                        name="name"
                        label="租户名称"
                    />
                    <ProFormSelect
                        width="md"
                        name="tenantLevel"
                        label="租户等级"
                        placeholder="请选择租户等级"
                        fieldProps={{
                            // size: 'large',
                        }}
                        options={[
                            {
                                value: 0,
                                label: '免费租户',
                            },
                            {
                                value: 1,
                                label: '1级租户',
                            },
                            {
                                value: 2,
                                label: '2级租户',
                            },
                            {
                                value: 3,
                                label: '3级租户',
                            },
                            {
                                value: 1000,
                                label: '自定义租户',
                            },
                        ]}
                        rules={[{ required: true }]}
                    />
                    <ProFormDatePicker
                        width="md"
                        name="expireTime"
                        label="过期时间"
                        placeholder="请输入过期时间"
                        fieldProps={{
                            // size: 'large',
                        }}
                        rules={[{ required: true }]}
                    />
                </ProForm>
            </Drawer>
        </PageContainer>
    );
};