import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'
import Layout from '@/layout'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/register'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      const hasGetUserInfo = store.getters.name
      if (hasGetUserInfo) {
        next()
      } else {
        try {
          // get user info
          await store.dispatch('user/getInfo')
          const sortedMenuList = sortMenuList(store.getters.menuList);
          // 路由转换
          let myRoutes = myFilterAsyncRoutes(sortedMenuList)

          // 404
          myRoutes.push({
            path: '*',
            redirect: '/404',
            hidden: true
          })
          console.log('过了404页面')
          // 动态添加路由
          router.addRoutes(myRoutes)
          // 存至全局变量
          global.myRoutes = myRoutes
          next({ ...to, replace: true }) // 防止刷新白屏
          // next()
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
// 动态路由导入
function myFilterAsyncRoutes(menuList) {
  menuList.filter(menu => {
    if (menu.component === 'Layout') {
      menu.component = Layout
      console.log(menu)
    } else {
      menu.component = require(`@/views/${menu.component}.vue`).default
    }

    // 递归处理子菜单
    if (menu.children && menu.children.length) {
      menu.children = myFilterAsyncRoutes(menu.children)
    }
    return true
  })
  return menuList
}

// 对菜单进行排序
function sortMenuList(menuList) {
  // 排序方法
  menuList.sort((a, b) => a.number - b.number)
  for (const menu of menuList) {
    if (menu.children && menu.children.length) {
      // 递归处理子菜单
      menu.children = sortMenuList(menu.children)
    }
  }
  return menuList
}
