import request from '@/utils/request'

// 查询巡查管理列表
export function listPatrol(query) {
  return request({
    url: '/system/patrol/list',
    method: 'get',
    params: query
  })
}

// 查询巡查管理详细
export function getPatrol(patrolId) {
  return request({
    url: '/system/patrol/' + patrolId,
    method: 'get'
  })
}

// 新增巡查管理
export function addPatrol(data) {
  return request({
    url: '/system/patrol',
    method: 'post',
    data: data
  })
}

// 修改巡查管理
export function updatePatrol(data) {
  return request({
    url: '/system/patrol',
    method: 'put',
    data: data
  })
}

// 删除巡查管理
export function delPatrol(patrolId) {
  return request({
    url: '/system/patrol/' + patrolId,
    method: 'delete'
  })
}
