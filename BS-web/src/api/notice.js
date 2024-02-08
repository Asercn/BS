import request from "@/utils/request";

export default {
  getAllNotice(searchModel) {
    return request({
      url: '/notice',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        title: searchModel.title
      }
    })
  },
  getNoticeById(id) {
    return request({
      url: `/notice/${id}`,
      method: 'get'
    })
  },
  deletedNoticeById(id) {
    return request({
      url: `/notice/${id}`,
      method: 'delete'
    })
  },
  updateNotice(noticeForm) {
    return request({
      url: '/notice',
      method: 'put',
      data: noticeForm
    })
  },
  addNotice(noticeForm) {
    return request({
      url: '/notice',
      method: 'post',
      data: noticeForm
    })
  },
  saveNoticeForm(noticeForm) {
    if (noticeForm.id === null || noticeForm.id === undefined) {
      return this.addNotice(noticeForm)
    } else {
      return this.updateNotice(noticeForm)
    }
  },
  getNoticeForDashboard() {
    return request({
      url: '/notice/dashboard',
      method: 'get'
    })
  }
}
