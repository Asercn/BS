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
  }
}

