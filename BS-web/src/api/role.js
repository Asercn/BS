import request from '@/utils/request'

export default {
  getRole() {
    return request({
      url: '/role/all',
      method: 'get'
    })
  },
  getRoleInfo(searchModel) {
    return request({
      url: '/role',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        roleName: searchModel.roleName
      }
    })
  },
  getRoleInfoById(id) {
    return request({
      url: `/role/${id}`,
      method: 'get'
    })
  },
  updateRole(Form) {
    return request({
      url: '/role',
      method: 'put',
      data: Form
    })
  },
  addRole(Form) {
    return request({
      url: '/role',
      method: 'post',
      data: Form
    })
  },
  saveRole(Form) {
    if (Form.roleId === null || Form.roleId === undefined) {
      // alert('add')
      return this.addRole(Form)
    } else {
      // alert('update')
      return this.updateRole(Form)
    }
  },
  deleteRole(Form) {
    return request({
      url: '/role',
      method: 'delete',
      data: Form
    })
  }
}
