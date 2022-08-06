<template>
  <h1>APP跟组件</h1>
  <Swiper></Swiper>
  <Header title="勇士总冠军" bgcolor="gold" color="black"></Header>
  <Level1></Level1>
  <p>-- 品牌:{{ name }}--价格:{{ price }}</p>
  <!-- <button @click="change">changeColor</button>
  <button @click="postInfo">postinfo</button> -->
</template>
<script>
import Swiper from './components/globalReg/Swiper.vue'
import Header from './components/globalReg/Header.vue'
import Level1 from './components/transport/Level1.vue'
import { reactive,toRefs,provide } from 'vue'
export default {
  name: 'App',
  components:{
    Swiper,
    Header,
    Level1
  },
  setup(){
    let car = reactive({name:'奔驰',price:'40W'})
    // provide()用于祖孙组件间传讯
    provide('car',car)

    // function change() {
    //   this.color = 'blue'
    // }

    // async function postInfo() {
    //   const { data:res } = await this.$http.post('api/post', {name: 'zs', age: 20})
    //   console.log(res)
    // }

    return {
      car,
      ...toRefs(car)

    }
  },
  // 如果想要给Level1组件里的Level2组件(孙组件)传数据,就需要定义provide(){}，来传递数据给孙组件
  // 孙组件中通过inject:['color']来接受color值
}
</script>

<style lang="less" scoped>
h1{
  color: pink;
}
</style>