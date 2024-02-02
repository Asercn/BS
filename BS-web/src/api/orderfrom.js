import request from '@/utils/request'

export default {
  addOrder(income, orderNumber) {
    const orderfrom = {
      income: income,
      orderNumber: orderNumber
    }
    return request({
      url: '/orderfrom/add',
      method: 'post',
      data: orderfrom
    })
  },
  getOrderfrom(searchModel) {
    return request({
      url: '/orderfrom/getorder',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        orderNumber: searchModel.orderNumber,
        date: searchModel.date
      }
    })
  },
  getIncome() {
    return request({
      url: '/orderfrom/getIncome',
      method: 'get'
    })
  }
}
