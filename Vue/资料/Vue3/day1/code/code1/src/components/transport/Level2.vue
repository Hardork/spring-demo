<template>
  <div>
    <h2>level2</h2>
    <p>从App组件中获取的car属性--{{ car.name }}--{{car.price}}</p>
  </div>
</template>

<script>
import {inject} from 'vue'
export default {
  name:'Level2',
  setup() {
    // 利用inject接受App.vue中传来的数据
    let car = inject('car')

    return {
      car
    }
  }
}
</script>

<style lang="less" scoped>

</style>