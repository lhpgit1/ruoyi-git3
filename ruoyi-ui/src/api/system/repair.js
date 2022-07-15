import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listRepair(query) {
  return request({
    url: '/system/repair/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getRepair(repairId) {
  return request({
    url: '/system/repair/' + repairId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addRepair(data) {
  return request({
    url: '/system/repair',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRepair(data) {
  return request({
    url: '/system/repair',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delRepair(repairId) {
  return request({
    url: '/system/repair/' + repairId,
    method: 'delete'
  })
}
