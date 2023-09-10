import request from '@/utils/request'

export default {
  getRoleInfo() {
    return request({
      url: '/role',
      method: 'get'
    })
  }
}
