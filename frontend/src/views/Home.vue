<template>
  <div id="home">
    <div class="wrap container">
      <SearchbarWeb/>
      <div class="container home-inner">
        <Content/>
        <Join/>
      </div>
    </div>
  </div>
</template>

<script>
import Content from '@/components/home/Content.vue'
import Join from '@/components/home/Join.vue'
import SearchbarWeb from '@/components/home/SearchbarWeb.vue'

export default {
  name: 'Home',
  components: {
    Content,
    Join,
    SearchbarWeb,
  }
}
</script>

<style>
  @media (min-width: 768px) and (max-width: 999px) {
    #home .wrap {
      margin-left: 250px;
    }
  }
  @media (min-width: 1000px) {
    #home-inner {
      width: 768px;
    }
    #home .wrap {
      display: inline-block;
      width: 80%;
    }
    #home {
      margin-left: 25%;
    }
  }
</style>