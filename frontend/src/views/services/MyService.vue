<template>
    <div id="myservice" class="container">
        <GoBack/>
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
import GoBack from '@/components/common/GoBack.vue'

export default {
    name: 'MyService',
    components:{
        GoBack,
        Button,
        ServiceList
    },methods:{
        toServiceAdd() {
        this.$router.push("/service/create");
    }

    },
    created() {
        this.$emit('sidebar')
        if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        axios.get(`${HTTP.BASE_URL}/service/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            this.services = res.data
         
        })
        .catch(err => {
            console.log(err)
        })
    },
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
<style>
    #myservice {
        margin-top: 50px;
    }
    #myservice .button-box {
        display: flex;
        justify-content: flex-end;
    }
    #myservice .service-box {
        margin-top: 30px
    }
    #myservice h3 {
        font-size: 2rem;
    }
</style>