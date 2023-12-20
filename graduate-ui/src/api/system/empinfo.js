import request from '@/utils/request'

// 查询就业信息发布列表
export function listEmpinfo(query) {
  return request({
    url: '/system/empinfo/list',
    method: 'get',
    params: query
  })
}

// 查询就业信息发布详细
export function getEmpinfo(empId) {
  return request({
    url: '/system/empinfo/' + empId,
    method: 'get'
  })
}

// 新增就业信息发布
export function addEmpinfo(data) {
  return request({
    url: '/system/empinfo',
    method: 'post',
    data: data
  })
}

// 修改就业信息发布
export function updateEmpinfo(data) {
  return request({
    url: '/system/empinfo',
    method: 'put',
    data: data
  })
}

// 删除就业信息发布
export function delEmpinfo(empId) {
  return request({
    url: '/system/empinfo/' + empId,
    method: 'delete'
  })
}

// 调整发布状态
export function modifiedEmpinfo(empId,empStatus) {
  return request({
    url: '/system/empinfo/'+empId+"/"+empStatus,
    method: 'put',
  })
}

// 查询就业信息发布列表不分页
export function noPageList(query) {
  return request({
    url: '/system/empinfo/noPageList',
    method: 'get',
    params: query
  })
}
