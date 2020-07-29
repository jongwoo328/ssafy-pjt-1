<template>
  <div class="search-result-card col-6 col-xl-4 font-kor">
      <div class="card-wrap">
        <div class="card-img">
            <img :src="service.imgUrl" alt="">
            <div class="card-cover">
                <h3>title</h3>
                <p>content</p>
            </div>
        </div>
        <div class="card-desc">
            <span>TEST</span>
            <div class="badges">
                <Badge 
                badgeColor="blueviolet"
                badgeText="USER"
                badgeCount="0" />
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
    }
    .card-cover:hover {
        background-color: rgba(0,0,0,0.5);
        color: black;
    }
    .card-img {
        position: relative;
        cursor: pointer;
    }
    .card-img img{
        max-width: 100%;
        max-height: 100%;
    }
    .card-desc {
        padding: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    .card-desc .badges {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
    }
    } */
    .card-wrap:hover {
        box-shadow: 0 1px 5px gray;
    }
</style>