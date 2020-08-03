<template>
    <div class="container">
        <h3>내 서비스</h3>
        <hr>
        <div class="button-box">
            <Button button-text="추가"/>        
        </div>
        <div v-if="!isService">
               <p>등록한 서비스가 없습니다.</p>
        </div>
        <div class="service-box">
           <SearchResultCard :services="serviceResult"/>   
        </div>  
    </div>
</template>
<script>
import Button from '@/components/common/Button.vue'
import SearchResultCard from '@/components/search/SearchResultCard.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    name: 'MyService',
    components:{
        
        Button,
        SearchResultCard
    },
    created() {
        axios.get(`${URL.BASE_URL}/service/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            console.log(res)
            this.serviceData = {
                imgUrl: 'http://172.30.1.13:8090/' + res.data.imgurl,
                comment: res.data.comment
            }
            console.log(this.serviceData.imgUrl)
            console.log(this.serviceData.title)
            if (res.data === 'fail') {
                this.isService = false
            }
        })
        .catch(err => {
            console.log(err)
        })
    },
    data(){
       return {
            profileFrame: false,
            isService: false,
            serviceData: "test",
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
