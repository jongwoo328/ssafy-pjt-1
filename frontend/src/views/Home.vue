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
          imgUrl: 'https://grepp-programmers.s3.amazonaws.com/production/company/logo/2640/_nolbal_bi_logo_%E1%84%89%E1%85%A6%E1%84%85%E1%85%A9.png',
          s_no: 1
        },
        {
          imgUrl: 'https://grepp-programmers.s3.amazonaws.com/production/company/logo/2640/_nolbal_bi_logo_%E1%84%89%E1%85%A6%E1%84%85%E1%85%A9.png',
          s_no: 2
        },
        {
          imgUrl: 'https://grepp-programmers.s3.amazonaws.com/production/company/logo/2640/_nolbal_bi_logo_%E1%84%89%E1%85%A6%E1%84%85%E1%85%A9.png',
          s_no: 3
        },
      ]
    }
  }
}
</script>

<style>
  @media (min-width: 768px) and (max-width: 999px) {
    #home .wrap {
      margin-left: 200px;
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