import request from '@/utils/request'

// 查询公告管理列表
export function listInfo(query) {
  return request({
    url: '/system/info/list',
    method: 'get',
    params: query
  })
}

// 查询公告管理详细
export function getInfo(nId) {
  return request({
    url: '/system/info/' + nId,
    method: 'get'
  })
}

// 新增公告管理
export function addInfo(data) {
  return request({
    url: '/system/info',
    method: 'post',
    data: data
  })
}

// 修改公告管理
export function updateInfo(data) {
  return request({
    url: '/system/info',
    method: 'put',
    data: data
  })
}

// 删除公告管理
export function delInfo(nId) {
  return request({
    url: '/system/info/' + nId,
    method: 'delete'
  })
}
