import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'


/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
  },
  {
    name: 'register',
    path: '/register',
    component: () => import('@/views/register/index') // 注册页面的组件
  },
  { // 个人信息
    path: '/personal',
    component: Layout,
    hidden: true,
    children: [{
      name: 'personal',
      path: '',
      component: () => import('@/views/personal/index')
    }]
  }

  // {
  //   path: '/user',
  //   component: Layout,
  //   children: [{
  //     path: '',
  //     component: () => import('@/views/user/index'),
  //     meta: { title: '测试', icon: 'dashboard' }
  //   }]
  // }
  // {
  //   path: '/room',
  //   component: Layout,
  //   redirect: '/room/boroom',
  //   name: 'room',
  //   meta: { title: '客房状态', icon: 'tree' },
  //   children: [
  //     {
  //       path: 'boroom',
  //       name: 'boroom',
  //       component: () => import('@/views/room/boroom'),
  //       meta: { title: '开房记录', icon: 'el-icon-s-custom' }
  //     },
  //     {
  //       path: 'oroom',
  //       name: 'oroom',
  //       component: () => import('@/views/room/oroom'),
  //       meta: { title: '房间状态', icon: 'el-icon-house' }
  //     }]
  // },
  // {
  //   path: '/roommanagement',
  //   component: Layout,
  //   redirect: '/roommanagement',
  //   children: [{
  //     path: '',
  //     name: 'roommanagement',
  //     component: () => import('@/views/roommanagement/roommanagement'),
  //     meta: { title: '房间管理', icon: 'el-icon-s-home' }
  //   }]
  // },
  // {
  //   path: '/user',
  //   component: Layout,
  //   redirect: '/user',
  //   children: [{
  //     path: '',
  //     name: 'name',
  //     component: () => import('@/views/user/index'),
  //     meta: { title: '用户管理', icon: 'el-icon-user-solid' }
  //   }]
  // },
  // {
  //   path: '/role',
  //   component: Layout,
  //   redirect: '/role',
  //   children: [{
  //     path: '',
  //     name: 'role',
  //     component: () => import('@/views/role/index'),
  //     meta: { title: '角色管理', icon: 'el-icon-s-custom' }
  //   }]
  // },
  //
  // // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
