import request from '@/utils/request'

// 查询签到列表
export function listCheck(query) {
  return request({
    url: '/system/check/list',
    method: 'get',
    params: query
  })
}

// 查询签到详细
export function getCheck(ckId) {
  return request({
    url: '/system/check/' + ckId,
    method: 'get'
  })
}

// 新增签到
export function addCheck(data) {
  return request({
    url: '/system/check',
    method: 'post',
    data: data
  })
}

// 修改签到
export function updateCheck(data) {
  return request({
    url: '/system/check',
    method: 'put',
    data: data
  })
}

// 删除签到
export function delCheck(ckId) {
  return request({
    url: '/system/check/' + ckId,
    method: 'delete'
  })
}
