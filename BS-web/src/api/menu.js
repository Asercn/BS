import request from '@/utils/request'

export default {
  getAllMenu() {
    return request({
      url: '/menu',
      method: 'get'
    })
  },
  addMenu(menuForm) {
    return request({
      url: '/menu',
      method: 'post',
      data: menuForm
    })
  }
}
