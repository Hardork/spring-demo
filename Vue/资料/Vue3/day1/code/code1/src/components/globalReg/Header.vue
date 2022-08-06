<template>
  <div class="header" :style="{width: 100+'%', height: 60+'px', 'line-height': 60+'px','text-align': center, 'color': color, 'background-color': bgcolor}">
  {{title}}
  </div>
</template>

<script>
  export default {
    name:'Header',
    props:{
      title:{
        default:'',
      },
      color:{
        default:'#fff',
      },
      bgcolor:{
        default:'blue'
      }
    }
  }
</script>

<style lang="less" scoped>
.header{
  position: fixed;
  left: 0;
  top: 0;
  z-index: 999;
}
</style>