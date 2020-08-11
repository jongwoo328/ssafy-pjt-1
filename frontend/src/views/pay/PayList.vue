<template>
    <div id="payList" class="container font-kor">
        <div>
            <h3>결제 내역</h3>
            <hr>
            <div>
            <ServiceList :services="services"/>
            </div>
        </div>
    </div>
</template>

<script>
import ServiceList from '@/components/service/ServiceList.vue'
import axios from 'axios'
import HTTP from '@/util/http-common.js'

export default {
    name:'PayList',
    components:{
        ServiceList
    },
    data(){
        return {
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
        console.log(this)
        console.log(1)
        axios.get(`${HTTP.BASE_URL}/pay/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            console.log(res)
            this.services = res.data
         
        })
        .catch(err => {
            console.log(err)
        })
    },    
}
</script>
<style>
    #payList {
        margin-top: 50px;
    }
    #payList h3 {
        font-size: 2rem;
    }
</style>