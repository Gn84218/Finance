import { PageContainer, ProForm, ProFormText, ProFormSelect, ProFormRadio, ProFormSwitch, ProFormCheckbox, ProFormDatePicker } from '@ant-design/pro-components';
import { Card, Form, Row, Col, Button, Input, Select, Radio, Switch, DatePicker, message, Checkbox, Cascader } from 'antd';
import React, { useState, useEffect } from 'react';
import { getInventory, updateInventory } from '@/services/ant-design-pro/assistCalculateSummary';
import { history } from 'umi';
import { response } from 'express';

const AssistCalculateInventoryDetail: React.FC = (props) => {
    const [form] = Form.useForm();
    const { p } = props;

    const [regionData, setRegionData] = useState([]);
    const [selectedRegionValues, setSelectedRegionValues] = useState([]);

    const onFinish = async (values: any) => {
        // let provinceCityCountryCode: string[] = form.getFieldValue("provinceCityCountryCode").split(',');

        let provinceCode = "";
        let cityCode = "";
        let countyCode = "";
        if (selectedRegionValues.length > 0) {
            provinceCode = selectedRegionValues[0];
            cityCode = selectedRegionValues[1];
            countyCode = selectedRegionValues[2];
        }

        let params = {
            ...values,
            id: p.id,
            disable: !form.getFieldValue("disable"),
            provinceCode: provinceCode,
            cityCode: cityCode,
            countyCode: countyCode
        };
        console.log(params);
        await updateInventory(params).then(response => {
            if (response.data) {
                message.success("修改成功");
                props.updateIsModalOpenState(false);
            }
            else {
                message.error("修改失败");
            }
        }).catch(error => {
            //console.log(error);
        });

    };

    // 在组件加载时调用接口
    useEffect(() => {
    }, []);
    return (
        <ProForm
            form={form}
            onFinish={onFinish}
            layout="vertical"
            request={async (params = {}) => {
                let data = await getInventory({ id: p.id }).then(response => {
                    return { ...response.data };
                });
                return data;
            }}
        >
            <ProForm.Group>
                <ProFormText
                    width="md"
                    name="code"
                    label="存货编码"
                    placeholder="请输入存货编码"
                    rules={[{ required: true }]}
                />
                <ProFormText
                    width="md"
                    name="name"
                    label="存货名称"
                    placeholder="请输入存货名称"
                    rules={[{ required: true, max: 50 }]}
                />

            </ProForm.Group>
            <ProForm.Group>
                <ProFormText
                    width="md"
                    name="specifications"
                    label="规格型号"
                    placeholder="请输入规格型号"
                    rules={[{ max: 50 }]}
                />


                <ProFormSelect
                    width="md"
                    name="inventoryCate"
                    label="存货类别"
                    placeholder="请输入存货类别"
                    options={[
                        { label: '原材料', value: '原材料' },
                        { label: '低值易耗品', value: '低值易耗品' },
                        { label: '在产品', value: '在产品' },
                        { label: '半成品', value: '半成品' },
                        { label: '库存商品', value: '库存商品' },
                    ]}
                >

                </ProFormSelect>

            </ProForm.Group>
            <ProForm.Group>
                <ProFormText
                    width="md"
                    name="units"
                    label="计量单位"
                    placeholder="请输入计量单位"
                />
                <ProFormDatePicker
                    width="md"
                    name="startDate"
                    label="启用日期"
                    placeholder="请输入启用日期"
                />
            </ProForm.Group>
            <ProForm.Group>
                <ProFormDatePicker
                    width="md"
                    name="endDate"
                    label="停用日期"
                    placeholder="请输入停用日期"
                />
            </ProForm.Group>

            <ProFormText
                width="md"
                name="notes"
                label="备注"
                placeholder="请输入备注"
                rules={[{ max: 500 }]}
                fieldProps={{ style: { width: '61%' } }}
            />

            <ProForm.Group>
                <ProFormSwitch width="md" name="disable" label="是否启用" rules={[{ required: true }]} checkedChildren="启用" unCheckedChildren="停用" initialValue={true} />
            </ProForm.Group>
        </ProForm>
    );
};

export default AssistCalculateInventoryDetail;