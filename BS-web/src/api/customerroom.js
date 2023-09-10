import request from '@/utils/request'

export default {
  getLastCustomerRoomByRoomId(Id) {
    return request({
      url: `/customerRoom/last/${Id}`,
      method: 'get'
    })
  },
  addCustomerRoom(customerRoom) {
    return request({
      url: '/customerRoom',
      method: 'post',
      data: customerRoom
    })
  }
}
