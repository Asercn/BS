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
  },
  updateRoom(roomForm) {
    return request({
      url: '/room',
      method: 'put',
      data: roomForm
    })
  },
  deleteRoom(roomId) {
    return request({
      url: `/room/${roomId}`,
      method: 'delete'
    })
  },
  getRoomById(roomId) {
    return request({
      url: `/room/${roomId}`,
      method: 'get'
    })
  },
  saveRoom(roomForm) {
    if (roomForm.roomId === null || roomForm.roomId === undefined) {
      // alert('add')
      return this.addRoom(roomForm)
    } else {
      // alert('update')
      return this.updateRoom(roomForm)
    }
  }

}

