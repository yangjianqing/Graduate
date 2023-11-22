import request from '@/utils/request'

// 查询学校管理列表
export function listSchool(query) {
  return request({
    url: '/system/school/list',
    method: 'get',
    params: query
  })
}

// 查询学校管理详细
export function getSchool(sId) {
  return request({
    url: '/system/school/' + sId,
    method: 'get'
  })
}

// 新增学校管理
export function addSchool(data) {
  return request({
    url: '/system/school',
    method: 'post',
    data: data
  })
}

// 修改学校管理
export function updateSchool(data) {
  return request({
    url: '/system/school',
    method: 'put',
    data: data
  })
}

// 删除学校管理
export function delSchool(sId) {
  return request({
    url: '/system/school/' + sId,
    method: 'delete'
  })
}
