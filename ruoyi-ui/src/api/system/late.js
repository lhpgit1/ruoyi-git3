import request from '@/utils/request'

// 查询夜归管理列表
export function listLate(query) {
  return request({
    url: '/system/late/list',
    method: 'get',
    params: query
  })
}

// 查询夜归管理详细
export function getLate(lateId) {
  return request({
    url: '/system/late/' + lateId,
    method: 'get'
  })
}

// 新增夜归管理
export function addLate(data) {
  return request({
    url: '/system/late',
    method: 'post',
    data: data
  })
}

// 修改夜归管理
export function updateLate(data) {
  return request({
    url: '/system/late',
    method: 'put',
    data: data
  })
}

// 删除夜归管理
export function delLate(lateId) {
  return request({
    url: '/system/late/' + lateId,
    method: 'delete'
  })
}
