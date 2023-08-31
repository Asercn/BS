import request from '@/utils/request'

export default {
  getCustomerRoomById(Id) {
    return request({
      url: `/customerRoom/${Id}`,
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
