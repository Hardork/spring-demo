<template>
  <div class="right-container">
    <h3>Right 组件</h3>
    <p>count:{{count}}</p>
    <button @click="add">+1</button>
    <p>msg的内容是:{{ msgFromLeft }}</p>
    <hr />

  </div>
</template>

<script>
// 1.导入 eventBus.js 模块
import bus from '@/components/eventBus'

export default {
  // 子组件自己的数据,TODO：把count的值传递给父组件
  data() {
    return {
      count:0,
      msgFromLeft:''
    }
  },
  methods: {
    add() {
      this.count += 1
      // 当this.count的值发生变化时, 通过 $emit() 触发自定义事件,
      this.$emit('numchange', this.count)
    }
  },
  // 组件一开始就监听share事件是否触发
  created() {
    // val存放在兄弟组件发送过来的内容
    bus.$on('share', (val)=> {
      this.msgFromLeft = val
    })
  },
}
</script>

<style lang="less">
.right-container {
  padding: 0 20px 20px;
  background-color: lightskyblue;
  min-height: 250px;
  flex: 1;
}
</style>
