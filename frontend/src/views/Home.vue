<template>
  <div id="home container">
    <div class="wrap container">
      <SearchbarWeb/>
      <div class="container home-inner">
        <Content/>
        <Join/>
      </div>
      <div id="search-result-wrap row">
        <SearchResultCard :services="serviceResult"/>
      </div>
    </div>
  </div>
</template>

<script>
import Content from '@/components/home/Content.vue'
import Join from '@/components/home/Join.vue'
import SearchbarWeb from '@/components/home/SearchbarWeb.vue'
import SearchResultCard from '@/components/search/SearchResultCard.vue'

export default {
  name: 'Home',
  components: {
    Content,
    Join,
    SearchbarWeb,
    SearchResultCard,
  },
  data() {
    return {
      serviceResult: [
        // 예시 표시용
        {
          s_no: 1,
          s_name: '서비스 이름',
          description: '서비스 설명',
          seraddr: '주소',
          price: '가격',
          u_no: '프로',
          c_no: 123123
        }
      ]
    }
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
  }
</style>