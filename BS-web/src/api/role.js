import request from '@/utils/request'

export default {
  getRoleInfo() {
    return request({
      url: '/role',
      method: 'get'
    })
  },
  getRoleInfoById(id) {
    return request({
      url: `/role/${id}`,
      method: 'get'
    })
  }
}
