<template>
    <div class="container">
        <h3>내 서비스</h3>
        <hr>
        <div class="button-box">
            <Button button-text="추가"/>        
        </div>
        <div class="service-box">
           <SearchResultCard :services="serviceResult"/>   
        </div>  
    </div>
</template>
<script>
import Button from '@/components/common/Button.vue'
import SearchResultCard from '@/components/search/SearchResultCard.vue'

export default {
    name: 'MyService',
    components:{
        
        Button,
        SearchResultCard
    },
    data(){
       return {
      serviceResult: [
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
    },
    computed:{
        toServiceAdd(){
            return '/';
        }
    }
}
</script>
<style scoped>
    .button-box {
        display: flex;
        justify-content: flex-end;
    }
    .service-box {
        margin-top: 30px
    }
</style>