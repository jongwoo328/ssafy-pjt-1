<template>
  <div class="search-result-card col-6 col-xl-4 font-kor">
      <div class="card-wrap">
        <div class="card-img">
            <img :src="getImgUrl" alt="service_image">
            <div class="card-cover" @click="changeDetail">
                <h3 v-text="service.servname"></h3>
            </div>
        </div>
        <div class="card-desc">
            <span v-text="service.servname"></span>
            <div class="badges">
                <Badge 
                badgeColor="forestgreen"
                badgeText="평점"
                :badgeCount="service.avgpoint" />
                <Badge 
                badgeColor="blueviolet"
                badgeText="USER"
                :badgeCount="service.payCount" />
            </div>
        </div>
      </div>
  </div>
</template>

<script>
import Badge from '@/components/common/Badge.vue'
import URL from '@/util/http-common.js'

export default {
    name: 'ServiceListItem',
    props: {
        service: Object
    },
    components:{
        Badge
    },
    computed: {
        getImgUrl() {
            console.log(this.service)
            return `${URL.BASE_URL}/${this.service.imgurl}`
        }
    },methods:{
        changeDetail(){
            console.log(1)
            this.$router.push(`/services/${this.service.servno}`)
        }
    }
    
}
</script>

<style>
    .search-result-card {
        display: inline-block;
        padding: 5px 10px 5px 10px;
    }
    /* .card-inner:hover {
        background-color: whitesmoke;
        cursor: pointer;
    }
    .card-inner {
        border-radius: 7px;
        box-shadow: 0 1px 5px gray;
        padding: 7px;
    }
    .card-inner header {
        position: relative;
        display: flex;
        justify-content: space-between;
        padding: 5px;
    }
    .card-inner header h2 {
        font-size: 1.25rem;
        font-weight: bold;
    }
    .card-inner hr {
        margin-top: 0;
        margin-bottom: 5px;
    }
    .card-inner footer {
        padding: 5px;
        display: flex;
        justify-content: space-between;
    }
    .card-inner footer p {
        margin-bottom: 0;
    }
    .card-inner footer span, .card-inner footer .card-addr {
        font-weight: bold;
    } */
    .card-content {
        padding-left: 5px;
        padding-right: 5px;
    }
    .pro-info img{
        width: 50px;
        height: 50px;
    }
    .pro-info p {
        display: inline-block;
    }
</style>