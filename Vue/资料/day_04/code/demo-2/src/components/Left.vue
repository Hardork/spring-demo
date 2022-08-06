<template>
  <div class="left-container">
    <h3>Left 组件</h3>
    <p>Count的值是: {{ Count }}</p>
    <button @click="Count+=1">+1</button>
    <p>message的内容是 {{message}}</p>
    <p>userinfo的内容是 {{userinfo}}</p>
    <!-- 不建议这么做 -->
    <button @click="message = 'abc'">修改message</button>
    <button @click="userinfo = { address:'fz'}">修改地址</button>
    <button @click="send">发送信息给Right</button>
    <hr />


  </div>
</template>

<script>
// 1.导入 eventBus.js 模块
import bus from '@/components/eventBus'

export default {
  props:['init','message','userinfo'],
  data() {
    return {
      Count:this.init,
      str:'2023年勇士总冠军!!',
    }
  },
  methods: {
    send() {
      // 通过 eventBus.js 发送数据
      bus.$emit('share', this.str)
    },
    reset() {
      this.Count = 0
    }
  },
}
</script>

<!-- scoped 属性 防止样式全局化,你写的样式只在当前组件生效 -->
<style lang="less" scoped>
.left-container {
  padding: 0 20px 20px;
  background-color: orange;
  min-height: 250px;
  flex: 1;
}

h3 {
  color: red;
}

// h5[data-v-3c83f0b7]
// [data-v-3c83f0b7] h5

// 当使用第三方组件库的时候，如果有修改第三方组件默认样式的需求，需要用到 /deep/
/deep/ h5 {
  color: pink;
}
</style>