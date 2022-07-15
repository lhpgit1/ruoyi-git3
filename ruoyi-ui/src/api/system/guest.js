import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listGuest(query) {
  return request({
    url: '/system/guest/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getGuest(guestId) {
  return request({
    url: '/system/guest/' + guestId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addGuest(data) {
  return request({
    url: '/system/guest',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateGuest(data) {
  return request({
    url: '/system/guest',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delGuest(guestId) {
  return request({
    url: '/system/guest/' + guestId,
    method: 'delete'
  })
}
