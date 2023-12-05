import request from '@/utils/request'

export default {
  getBoRoomList() {
    return request({
      url: `/customerRoom/boroomList`,
      method: 'get'
    })
  },
  getCustomerRoomByRoomId(roomId) {
    return request({
      url: `/customerRoom/${roomId}`,
      method: 'get'
    })
  },
  addCustomerRoom(customerRoom) {
    return request({
      url: '/customerRoom',
      method: 'post',
      data: customerRoom
    })
  },
  outRoom(customerForm) {
    return request({
      url: '/customerRoom',
      method: 'put',
      data: customerForm
    })
  }
}
