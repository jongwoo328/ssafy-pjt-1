<template>
  <div class="search-result-card col-12 col-md-6 col-xl-4 font-kor">
      <div class="card-wrap">
        <div @click="changeDetail" class="card-img">
            <img :src="service.imgurl" alt="service_image">
            <div class="card-cover">
                <h3 v-text="service.servname"></h3>
            </div>
        </div>
        <div @click="changeDetail" class="card-desc">
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

export default {
    name: 'SearchResultCard',
    props: {
        service: Object
    },
    components: {
        Badge
    },
    computed: {
        
    },
    methods:{
        changeDetail(){
            this.$router.push(`/services/${this.service.servno}`)
        }
    }
    
}
</script>

<style>
    .search-result-card {
        display: inline-block;
        padding: 10px;
    }
    .card-cover {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: transparent;
        color: transparent;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border-top: 1px solid gray;
        border-left: 1px solid gray;
        border-right: 1px solid gray;
    }
    .card-cover:hover {
        background-color: rgba(0,0,0,0.5);
        color: whitesmoke;
    }
    .card-img {
        position: relative;
        cursor: pointer;
        height: 250px;
    }
    .card-img img{
        width: 100%;
        height: 100%;
        max-width: 100%;
        max-height: 100%;
        object-fit: contain;
    }
    .card-desc {
        padding: 10px;
        display: flex;
        min-height: 75px;
        flex-direction: column;
        justify-content: space-between;
        border-left: 1px solid gray;
        border-right: 1px solid gray;
        border-bottom: 1px solid gray;
    }
    .card-desc:hover {
        cursor: pointer;
    }
    .card-desc .badges {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
    }
    .card-wrap:hover {
        box-shadow: 0 1px 5px gray;
    }
</style>