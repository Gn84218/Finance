// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';


/** 币别列表 GET /file/list */
export async function list(
  params: {
    folderId?: number
  },
  options?: { [key: string]: any },
) {
  return request('/adminapi/file/list', {
    method: 'POST',
    data: params,
    skipErrorHandler: false,
    ...(options || {}),
  });
}