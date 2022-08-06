import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import axios from 'axios'

const app = createApp(App)

axios.defaults.baseURL = 'https://www.escook.cn'
app.config.globalProperties.$http = axios
// 全局注册组件
app.mount('#app')
