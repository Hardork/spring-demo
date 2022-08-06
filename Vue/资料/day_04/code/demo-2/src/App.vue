
<template>
<div>
  <!-- 定义ref属性,通过this.refs.名称 来获取DOM元素 -->
  <!-- ref属性的名称不能重复 -->
    <h1 ref="myh1">App 根组件--{{ countFromSon }}</h1>
    <button @click="show">打印this</button>
    <button @click="resetLeft">重置</button>
    <hr>

    <div class="box">
      <!-- 渲染Left和Right组件 -->
      <Left :init="Count" :message="message" :userinfo="userinfo" ref="comLeft"></Left>
      <Right @numchange="getNewCount"></Right>
    </div>

    <input type="text" v-if="inputVisible" @blur="hideIp" ref="iptref">
    <button @click="showIp" v-else>展示输入框</button>
</div>
</template>

<script>
// 1.导入组件
import Left from '@/components/Left.vue'
import Right from '@/components/Right.vue'
// 2.注册组件
export default {
  data() {
    return {
      message:'hello vue!',
      userinfo:{ name:'hwq', age:18},
      Count:10,
      // 定义 countFromSon 接受子组件传递过来的值
      countFromSon:0,
      // 控制输入框和按钮的按需转换
      inputVisible:false
    }
  },
  components:{
    Left,
    Right
  },
  methods: {
    // val 就是子组件的this.count
    getNewCount(val) {
      console.log('getNewCount事件被触发了!!');
      this.countFromSon = val
    },
    show() {
      // 获取当前<h1 ref="myh1">App 根组件--{{ countFromSon }}</h1>
      // console.log(this.$refs.myh1);
      this.$refs.myh1.style.color = 'red'
    },
    resetLeft() {
      this.$refs.comLeft.reset()
    },
    showIp() {
      this.inputVisible = true
      // 虽然把inputVisible改为了true但是,在这个函数中,dom并没被渲染出来
      // 所有this.$refs.iptref是undefined
      // 这时候需要利用this.$nextTick(cb)方法，将执行的语句放到下一个DOM重新渲染周期中去
      this.$nextTick(() => {
        this.$refs.iptref.focus()
      })
    },
    hideIp() {
      this.inputVisible = false
    }
  },
}

</script>