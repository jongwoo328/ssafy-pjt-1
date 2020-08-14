<template>
    <div id="payList" class="container font-kor">
        <GoBack/>
        <div>
            <h3>결제 내역</h3>
            <hr>

            <div v-if="isEmpty">
            <ServiceList :services="services"/>
            </div>
            <div v-else>
                <span class="main-text">결제한 서비스가 없습니다.</span>
            </div>
        </div>
    </div>
</template>

<script>
import ServiceList from '@/components/service/ServiceList.vue'
import axios from 'axios'
import HTTP from '@/util/http-common.js'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name:'PayList',
    components:{
        ServiceList,
        GoBack
    },
    data(){
        return {
            isEmpty:true,
            services:[]
        }
    },
    created() {
        if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        this.$emit('sidebar')

        axios.get(`${HTTP.BASE_URL}/pay/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            
            this.services = res.data
            console.log(this.services.length)
            if(this.services.length===0){
                this.isEmpty=false
            }
        })
        .catch(err => {
            console.log(err)
        })
    },    
}
</script>
<style>
    .main-text {
      font-size: 1rem;
    }
    #payList {
        margin-top: 50px;
    }
    #payList h3 {
        font-size: 2rem;
    }
</style>