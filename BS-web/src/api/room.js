import request from '@/utils/request'

export default {
  getRoom(searchModel) {
    return request({
      url: '/room/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        roomName: searchModel.roomname
      }
    })
  },
  getRoomInfo() {
    return request({
      url: '/room/info',
      method: 'get'
    })
  },
  getRoomData(searchModel) {
    return request({
      url: '/room/all',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize
      }
    })
  },
  addRoom(roomForm) {
    return request({
      url: '/room',
      method: 'post',
      data: roomForm
    })
  }
}

