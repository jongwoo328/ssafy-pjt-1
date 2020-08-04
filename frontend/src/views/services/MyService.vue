<template>
    <div class="container">
        <h3>내 서비스</h3>
        <hr>
        <div class="button-box">
            <Button type="submit" @click.native="toServiceAdd" button-text="추가" />        
        </div>
        <div v-if="!isService">
               <p>등록한 서비스가 없습니다.</p>
        </div>
        <div v-else class="service-box">
           <ServiceList :services="services"/>   
        </div>  
    </div>
</template>
<script>
import Button from '@/components/common/Button.vue'
import ServiceList from '@/components/service/ServiceList.vue'
import axios from 'axios'
import HTTP from '@/util/http-common.js'

export default {
    name: 'MyService',
    components:{
        
        Button,
        ServiceList
    },methods:{
        toServiceAdd() {
        this.$router.push("/service/create");
    }

    },
    created() {
        axios.get(`${HTTP.BASE_URL}/service/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            console.log(res)
            this.services = res.data
            this.isProfile=true
         
        })
        .catch(err => {
            console.log(err)
        })
    },
    // created() {
    // //     axios.get(`${URL.BASE_URL}/service/${this.$store.getters.getUserData.userno}`)
    // //     .then(res => {
    // //         console.log(res)
    // //         this.serviceData = {
    // //             imgUrl: 'http://172.30.1.13:8090/' + res.data.imgurl,
    // //             comment: res.data.comment
    // //         }
    // //         console.log(this.serviceData.imgUrl)
    // //         console.log(this.serviceData.title)
    // //         if (res.data === 'fail') {
    // //             this.isService = false
    // //         }
    // //     })
    // //     .catch(err => {
    // //         console.log(err)
    // //     })
    // },
    data(){
       return {
            profileFrame: false,
            isService: true,
            services: [],
      serviceResult: [
        {
          imgUrl: "",
          servno: "",
          servname : "",
          description : "",
          avgpoint : ""
        }
      ]
    }
    },
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