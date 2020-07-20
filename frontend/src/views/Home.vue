<template>
  <div id="home" class="container-fluid">
    <Content/>
    <Join/>
  </div>
</template>

<script>
import Content from '@/components/home/Content.vue'
import Join from '@/components/home/Join.vue'

export default {
  name: 'Home',
  components: {
    Content,
    Join
  }
}
</script>

<style scoped>
  @media (min-width: 768px) {
    #home {
      width: 768px;
    }
  }
</style>