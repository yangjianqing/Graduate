import request from '@/utils/request'

// 查询学生管理列表
export function listStudent(query) {
  return request({
    url: '/system/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生管理详细
export function getStudent(sId) {
  return request({
    url: '/system/student/' + sId,
    method: 'get'
  })
}



// 新增学生管理
export function addStudent(data) {
  return request({
    url: '/system/student',
    method: 'post',
    data: data
  })
}

// 修改学生管理
export function updateStudent(data) {
  return request({
    url: '/system/student',
    method: 'put',
    data: data
  })
}

// 删除学生管理
export function delStudent(sId) {
  return request({
    url: '/system/student/' + sId,
    method: 'delete'
  })
}
//初始化学生管理所需数据
export function initStudent() {
  return request({
    url: '/system/student/initStudent',
    method: 'get'
  })
}
