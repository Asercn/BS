import request from "@/utils/request";

export default {
  getServerInfo() {
    return request({
      url: '/monitor',
      method: 'get'
    })
  }
}
