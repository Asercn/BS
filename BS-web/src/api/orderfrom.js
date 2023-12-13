import request from '@/utils/request'

export default {
  addOrder(income, orderNumber) {
    const orderfrom = {
      income: income,
      orderNumber: orderNumber
    }
    return request({
      url: 'orderfrom/add',
      method: 'post',
      data: orderfrom
    })
  }
}
