<template>
  <div id="home container">
    <div class="wrap container">
      <SearchbarWeb/>
      <div class="container home-inner">
        <Content/>
        <Join/>
      </div>
      <div id="search-result-wrap row">
        <SearchResultCard :services="services"/>
      </div>
    </div>
  </div>
</template>

<script>
import Content from '@/components/home/Content.vue'
import Join from '@/components/home/Join.vue'
import SearchbarWeb from '@/components/home/SearchbarWeb.vue'
import SearchResultCard from '@/components/search/SearchResultCard.vue'
import axios from 'axios'
import HTTP from '@/util/http-common.js'
export default {
  name: 'Home',
  components: {
    Content,
    Join,
    SearchbarWeb,
    SearchResultCard,
  }, 
  created(){
        axios.get(`${HTTP.BASE_URL}/service/main`)
        .then(res => {
            console.log(res)
            this.services = res.data
            this.services.forEach(service => {
              service.imgurl = `${HTTP.BASE_URL}/${service.imgurl}`
            })
        })
        .catch(err => {
            console.log(err)
        })
  },
  data() {
    return {
       services: [],
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