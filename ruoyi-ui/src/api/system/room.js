import request from '@/utils/request'

// 查询宿舍房间号列表
export function listRoom(query) {
  return request({
    url: '/system/room/list',
    method: 'get',
    params: query
  })
}


// 查询宿舍房间号详细
export function getRoom(roomId) {
  return request({
    url: '/system/room/' + roomId,
    method: 'get'
  })
}

// 新增宿舍房间号
export function addRoom(data) {
  return request({
    url: '/system/room',
    method: 'post',
    data: data
  })
}

// 修改宿舍房间号
export function updateRoom(data) {
  return request({
    url: '/system/room',
    method: 'put',
    data: data
  })
}

// 删除宿舍房间号
export function delRoom(roomId) {
  return request({
    url: '/system/room/' + roomId,
    method: 'delete'
  })
}
