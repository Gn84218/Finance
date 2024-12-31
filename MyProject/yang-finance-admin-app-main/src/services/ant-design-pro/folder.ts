// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';


/** 币别列表 GET /currencyConfig/list */
export async function list(
  params: {
    pid?: number
  },
  options?: { [key: string]: any },
) {
  return request('/adminapi/folder/list', {
    method: 'GET',
    params: params,
    skipErrorHandler: false,
    ...(options || {}),
  });
}