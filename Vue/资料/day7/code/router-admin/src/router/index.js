import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
// 导入需要的组件
import Login from '@/components/MyLogin'
import Home from '@/components/MyHome'
import Users from '@/components/menus/MyUsers'
import Goods from '@/components/menus/MyGoods'
import Rights from '@/components/menus/MyRights'
import Settings from '@/components/menus/MySettings'
import Orders from '@/components/menus/MyOrders'
import UserInfo from '@/components/user/MyUserDetail'
import pathArr from '@/router/pathArr'





const Router = new VueRouter({
  routes: [
    // 登录组件
    { path: '/', component:Login },
    { path: '/login', component: Login },
    // 后台主页组件
    { 
    path: '/home', 
    component: Home,
    children:[
      { path: '/', component: Users },
      { path: 'users', component: Users },
      { path: 'goods', component: Goods },
      { path: 'orders', component: Orders },
      { path: 'settings', component: Settings },
      { path: 'rights', component: Rights },
      // 用户详情页的规则
      { path: 'userinfo/:id', component: UserInfo, props: true }, 


    ]
  }
  ]
})

// 路由的全局前置守卫
Router.beforeEach(function(to, from, next) {
  // 判断要去的地址,是否存在于pathArr中
  if(pathArr.indexOf(to.path) !== -1){
    //如果用户要直接进入主页,要判断是否具有token值
    const token = localStorage.getItem('token')
    if(token){
      //具有token 
      next()
    }
    else{
      //不具有token
      alert('请先登录!')
      next('/login')
    }
  }
  else{
    next()
  }
})

export default Router