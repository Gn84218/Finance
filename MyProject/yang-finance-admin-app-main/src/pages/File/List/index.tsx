import { ExclamationCircleOutlined, PlusOutlined, FolderOutlined, FolderOpenFilled, InboxOutlined } from '@ant-design/icons';
import { ActionType, PageContainer, ProColumns } from '@ant-design/pro-components';
import { ProTable, TableDropdown } from '@ant-design/pro-components';
import type { UploadProps } from 'antd';
import { Button, Dropdown, Space, Tag, Switch, Modal, message, Tree, Card, Row, Col, Form, DatePicker, Input, Upload } from 'antd';
import { useRef, useState, useEffect } from 'react';
import request from 'umi-request';
import { list } from '@/services/ant-design-pro/folder';
import { list as listFile } from '@/services/ant-design-pro/file';

import { Link } from 'umi';
import {
    FolderOpenOutlined,
    FileTextOutlined, FilePdfOutlined, FileImageOutlined, FileExcelOutlined, FileWordOutlined, FilePptOutlined
} from '@ant-design/icons';
import moment from 'moment';

const iconMap = {
    '.txt': <FileTextOutlined />,
    '.pdf': <FilePdfOutlined />,
    '.png': <FileImageOutlined />,
    '.jpg': <FileImageOutlined />,
    '.jpeg': <FileImageOutlined />,
    '.xls': <FileExcelOutlined />,
    '.xlsx': <FileExcelOutlined />,
    '.csv': <FileExcelOutlined />,
    '.word': <FileWordOutlined />,
    '.ppt': <FilePptOutlined />,
};
const getFileIcon = (extension: string) => {
    return iconMap[extension] || <div>extension</div>; // 如果没有匹配项，显示未知文件类型  
}
const { RangePicker } = DatePicker;
const { Dragger } = Upload;

interface DataNode {
    title: string;
    key: string;
    isLeaf?: boolean;
    children?: DataNode[];
}

const initTreeData: DataNode[] = [
    { title: 'Expand to load', key: '0' },
    { title: 'Expand to load', key: '1' },
    { title: 'Tree Node', key: '2', isLeaf: true },
];

// It's just a simple demo. You can use tree map to optimize update perf.
const updateTreeData = (list: DataNode[], key: React.Key, children: DataNode[]): DataNode[] =>
    list.map((node) => {
        if (node.key === key) {
            return {
                ...node,
                children,
            };
        }
        if (node.children) {
            return {
                ...node,
                children: updateTreeData(node.children, key, children),
            };
        }
        return node;
    });


const FileList: React.FC = () => {
    const [fileList, setFileList] = useState([]);
    const [treeData, setTreeData] = useState(initTreeData);
    const actionRef = useRef<ActionType>();
    const [dataSource, setDataSource] = useState<API.ListAccountBookVoItem[]>();
    const [form] = Form.useForm();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [folderId, setFolderId] = useState(0);

    let token = localStorage.getItem('token');
    const props: UploadProps = {
        name: 'file',
        multiple: true,
        maxCount: 20,
        fileList,
        action: '/adminapi/file/upload',
        headers: {
            "Api-Access-Token": `${token}`,
        },
        data: { folderId: folderId }, // 将 foldId 作为参数发送
        onChange(info) {
            const { status } = info.file;
            setFileList(info.fileList);

            if (status !== 'uploading') {
                console.log(info.file, info.fileList);
            }
            if (status === 'done') {
                message.success(`${info.file.name} 上传成功！`);
                form.submit();
            } else if (status === 'error') {
                message.error(`${info.file.name} 上传失败！`);
            }
        },
        onDrop(e) {
            console.log('Dropped files', e.dataTransfer.files);
        },
    };

    const onLoadData = ({ key, children }: any) =>
        new Promise<void>((resolve) => {
            if (children) {
                resolve();
                return;
            }
            list({ pid: key }).then(response => {
                const newData = response.data.map((item, index) => ({
                    title: item.name,
                    key: `${item.id}`, // 这里需要根据实际情况组合 key
                    // 其他可能的属性...
                }));
                setTreeData((origin) =>
                    updateTreeData(origin, key, newData),
                );
                resolve();
            }).catch(() => {

            });
        });


    const columns: ProColumns[] = [
        {
            title: <strong>ID</strong>,
            dataIndex: 'id',
            // valueType: 'number',
            search: false
        },
        {
            title: <strong>名称</strong>,
            dataIndex: 'name',
            ellipsis: true,
            search: false
            // tip: '标题过长会自动收缩',

        },
        {
            disable: true,
            title: <strong>文件类型</strong>,
            dataIndex: 'extension',
            filters: true,
            search: false,
            render: (text, record) => (
                <Space>
                    {getFileIcon(record.extension)}
                    <div>{record.extension}</div>
                </Space>
            ),
        },
        {
            title: <strong>上传日期</strong>,
            dataIndex: 'createTime',
            valueType: 'date', // 设置 valueType 为 'text'，将布尔值显示为文本
            search: false,
        },
        {
            title: <strong>操作</strong>,
            valueType: 'option',
            key: 'option',
            render: (text, record, _, action) => {
                return [
                    <Link to={"/FinanceSetting/CurrencyList/CurrencyDetail?id=" + record.id?.toString()} key="editable">
                        编辑
                    </Link>
                    ,
                    <a href={record.url} target='_blank'>
                        查看
                    </a>
                ]
            }
        },
    ];

    const showModal = () => {
        token = localStorage.getItem('token');
        setFileList([]);
        setIsModalOpen(true);
    };

    const handleOk = () => {
        setIsModalOpen(false);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };
    //查询文件列表
    const onSearchFileList = async (params?: any) => {
        console.log(params)
        params.folderId = folderId;
        params.pageNum = params.pageNum ? params.pageNum : 1;
        if (params.createDate) {
            params.beginDate = moment(params.createDate[0]).format('YYYY-MM-DD 00:00:00');
            params.endDate = moment(params.createDate[1]).format('YYYY-MM-DD 23:59:59');
        }
        return await listFile({ ...params }).then(response => {
            setDataSource(response.data.list);
            // setPagination({
            //     ...pagination,
            //     current: params.pageNum,
            //     total: response.data.total,
            // });
        }).catch(() => {

        });
    }



    useEffect(() => {
        list({ pid: 0 }).then(response => {
            const newData = response.data.map((item, index) => ({
                title: item.name,
                key: `${item.id}`, // 这里需要根据实际情况组合 key
                // 其他可能的属性...
            }));
            setTreeData(newData);
        }).catch(() => {

        });
    }, []); // 空数组表示仅在组件加载时调用一次
    return (
        <PageContainer>
            <Card style={{ marginBottom: 20 }} size='small'>
                <Form form={form} name="advanced_search"
                    onFinish={onSearchFileList}
                >
                    <Row gutter={24}>
                        <Col>
                            <Form.Item
                                label='上传日期'
                                name={'createDate'}
                            >
                                <RangePicker />
                                {/* <RangePicker /> */}
                            </Form.Item>
                        </Col>
                        <Col>
                            <Form.Item
                                label='文件名'
                                name={'name'}
                            >
                                <Input />
                            </Form.Item>
                        </Col>

                        <Col>
                            <Space>
                                <Button type="primary" htmlType="submit">
                                    查询
                                </Button>
                                <Button
                                    onClick={() => {
                                        form.resetFields();
                                    }}
                                >
                                    重置
                                </Button>
                            </Space>

                        </Col>
                    </Row>
                </Form>
            </Card>
            <Row gutter={[16, 16]}>
                <Col span={6}>
                    <Card title="文件夹" style={{ overflow: 'auto' }} size='small'>
                        <Tree loadData={onLoadData} treeData={treeData}
                            showLine
                            icon={({ expanded }) => (expanded ? <FolderOpenFilled /> : <FolderOutlined />)}
                            onSelect={(key)=>{
                                setFolderId(Number(key[0]));
                                form.submit();
                                //actionRef.current?.reload();
                            }}
                        // switcherIcon={customSwitcherIcon} // 设置自定义的展开和关闭图标
                        />
                    </Card>
                </Col>
                <Col span={18}>
                    <ProTable<API.ListAccountBookVoItem, API.PageParams>
                        bordered={true}
                        columns={columns}
                        actionRef={actionRef}
                        cardBordered
                        request={async (params = {}, sort, filter) => {
                            console.log(sort, filter);
                            let response = await listFile({ ...params, pageNum: params.current });

                            setDataSource(response.data.list);
                            return { data: response.data.list };
                        }}
                        dataSource={dataSource}
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
                        search={false}
                        options={{
                            setting: {
                                listsHeight: 400,
                            },
                        }}
                        form={{
                            // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
                            syncToUrl: (values, type) => {
                                if (type === 'get') {
                                    return {
                                        ...values,
                                    };
                                }
                                return values;
                            },
                        }}
                        pagination={false}
                        dateFormatter="string"
                        headerTitle="文件列表"
                        toolBarRender={() => [
                            <Button key="button" icon={<PlusOutlined />} type="primary" onClick={showModal}>
                                上传文件
                            </Button>,
                        ]}
                    />
                </Col>
            </Row>
            <Modal title="上传文件" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <Dragger {...props}>
                    <p className="ant-upload-drag-icon">
                        <InboxOutlined />
                    </p>
                    <p className="ant-upload-text">单击或拖动文件到此区域进行上传</p>
                    <p className="ant-upload-hint">
                        支持单次或批量上传。严禁上传公司数据或其他
                        被禁止的文件。
                    </p>
                </Dragger>
            </Modal>
        </PageContainer>
    );
};
export default FileList;