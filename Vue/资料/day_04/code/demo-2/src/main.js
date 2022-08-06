// 导入vue这个包
import Vue from 'vue'
// 导入 app.vue这个根组件，将来把app.vue 中的模板结构,渲染到html页面中
// import App from './App.vue'
import App from './App.vue'
// 导入需要全局注册的组件
// 全局注册的组件在其他组件中就不需要再导入了,直接使用
import Left from '@/components/Left.vue'

Vue.component('MyLeft',Left)


Vue.config.productionTip = false

//  创建Vue 的实例对象
new Vue({
  // 把 render 函数指定的组件,渲染到 HTML 页面中,会把#app的元素替换掉
  render: h => h(App),
}).$mount('#app')
// $mount('#app')和el:'#app'的作用相同,指定作用区域