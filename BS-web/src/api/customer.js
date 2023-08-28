import request from '@/utils/request'

export default {
  getcustomer(searchModel) {
    return request({
      url: '/customer/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        customerName: searchModel.customerName,
        customerPhone: searchModel.customerPhone,
        roomName: searchModel.roomName
      }
    })
  }
}

