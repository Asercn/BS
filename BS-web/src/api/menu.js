import request from '@/utils/request'

export default {
  getAllMenu() {
    return request({
      url: '/menu',
      method: 'get'
    })
  },
  addMenu(menuForm) {
    return request({
      url: '/menu',
      method: 'post',
      data: menuForm
    })
  },
  updateMenu(menuForm) {
    return request({
      url: '/menu',
      method: 'put',
      data: menuForm
    })
  },
  deleteMenu(menuId) {
    return request({
      url: `/menu/${menuId}`,
      method: 'delete'
    })
  },
  getMenuById(menuId) {
    return request({
      url: `/menu/${menuId}`,
      method: 'get'
    })
  },
  saveMenu(menuForm) {
    if (menuForm.menuId === null || menuForm.menuId === undefined) {
      return this.addMenu(menuForm)
    } else {
      console.log('update')
      return this.updateMenu(menuForm)
    }
  }
}
