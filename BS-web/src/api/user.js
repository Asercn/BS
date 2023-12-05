import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
export function getUserInfo(searchModel) {
  return request({
    url: '/user',
    method: 'get',
    params: {
      pageNo: searchModel.pageNo,
      pageSize: searchModel.pageSize,
      username: searchModel.username
    }
  })
}
export function getUserInfoOrByUserID(id) {
  return request({
    url: `/user/${id}`,
    method: 'get'
  })
}
export function deleteUserInfoByUserID(userID) {
  return request({
    url: `/user/${userID}`,
    method: 'delete'
  })
}
export function updateUser(userForm) {
  return request({
    url: '/user',
    method: 'put',
    data: userForm
  })
}
export function updateUserRole(userRoleForm) {
  return request({
    url: '/userRole',
    method: 'put',
    data: userRoleForm
  })
}
export function addUser(userForm) {
  return request({
    url: '/user/add',
    method: 'post',
    data: userForm
  })
}
