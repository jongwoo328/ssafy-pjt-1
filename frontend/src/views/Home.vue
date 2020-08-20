<template>
  <div id="home container">
    <div class="wrap container">
      <SearchbarWeb @child="searchs"/>
      <div  class="container home-inner">
        <Content v-if="!flagcomputed"/>
        <Join @child="searchs"/>
      </div>
      <div id="search-result-wrap row">
        <div class="mobile">
          <hr>
        </div>
      <h2 v-text="text"></h2>
        <SearchResultCard :services="services"/>
      </div>
    </div>
    <SeeMore 
    :seeMoreReset="seeMoreReset" 
    :seeMoreKeyword="seeMoreKeyword" 
    :seeMoreFlag="seeMoreFlag" 
    :state="state" @seemore="addService" 
    :searchData="searchData"
    @sidebar="this.$emit('sidebar')"/>
  </div>
</template>

<script>
import Content from '@/components/home/Content.vue'
import Join from '@/components/home/Join.vue'
import SearchbarWeb from '@/components/home/SearchbarWeb.vue'
import SearchResultCard from '@/components/search/SearchResultCard.vue'
import SeeMore from '@/components/home/SeeMore.vue'
import axios from 'axios'
import HTTP from '@/util/http-common.js'

export default {
  name: 'Home',
  components: {
    Content,
    Join,
    SearchbarWeb,
    SearchResultCard,
    SeeMore,
  },
  data() {
    return {
       services: [],
       searchInfo:[],
       search_on:false,
       text:"인기있는 서비스들",
       flag:false,

       state: 'main',
       searchData: {},
       seeMoreFlag: true,
       seeMoreKeyword: '',
       seeMoreReset: false,
    }
  },
  methods:{
    searchdata(data) {
      this.searchData = data
    },
    searchs(search){
      this.seeMoreKeyword = search.keyword
      search['num'] = 1
      if (search.cateno === undefined) {
        search['cateno'] = 0,
        search['saddr6'] = null
      }
      this.seeMoreReset = ! this.seeMoreReset
      axios.post(`${HTTP.BASE_URL}/service/search`,search, HTTP.JSON_HEADER)
      .then(res =>{
        if (res.data.length === 6) {
          this.seeMoreFlag = true
        }
        this.services=res.data
        this.services.forEach(service => {
                service.imgurl = `${HTTP.IMG_URL}/${service.imgurl}`
              })
        this.search_on=true
        this.text = search.keyword+" 검색결과"
        this.state = 'search'
  })
      .catch(err => {
        console.log(err)
      })
    },
    addService(results) {
      results.forEach(result => {
        result.imgurl = `${HTTP.IMG_URL}/${result.imgurl}`
        this.services.push(result)
      })
    }
  },
  computed:{
    isLoggedIn() {
            return this.$store.getters.isLoggedIn
        },
    flagcomputed(){
       return this.isLoggedIn || this.search_on
    },
  },
  created(){
    
    axios.get(`${HTTP.BASE_URL}/service/main/1`)
        .then(res => {
            this.services = res.data
            this.services.forEach(service => {
              service.imgurl = `${HTTP.IMG_URL}/${service.imgurl}`
          
            })
        })
        .catch(err => {
          console.log(err)
        })
    this.$store.dispatch('setMsgCount')
  },
  mounted() {
    this.$emit('sidebar')
    
  }
}
</script>

<style>
  @media (min-width: 768px) {
    .mobile {
      display: none;
    }
  }
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