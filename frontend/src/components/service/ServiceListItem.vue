<template>
  <div class="search-result-card col-12 col-md-6 col-xl-4 font-kor">
      <div class="card-wrap">
        <div class="card-img">
            <img :src="getImgUrl" alt="service_image">
            <div class="card-cover" @click="changeDetail">
                <h3 v-text="service.servname"></h3>
                <h3 v-text="formattedPrice+'원'"></h3>
            </div>
        </div>
        <div class="card-desc" v-if="!isPay">
            <span v-text="service.servname" ></span>
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
        <div class="card-desc pay-card" v-else>
            <div class="span-class">
            <span class="service-title" v-text="service.servname" ></span>
            <span id="pdate" v-text="service.pdate"></span>
            </div>
            <span v-text="formattedPrice+'원'"></span>
            <!-- <button id="returnpay" @click="pay">환불</button> -->
            <div class="button-class">
            <Button buttonText="결제취소" v-if="service.cancelcheck" @click.native="pay"></Button>
            <Button buttonText="취소불가" :buttonColor="color" v-else disabled id="btn_1"></Button>
            </div>
            </div>
      </div>
  </div>
</template>

<script>
import Badge from '@/components/common/Badge.vue'
import HTTP from '@/util/http-common.js'
import axios from 'axios'
import Button from '@/components/common/Button.vue'
import Common from '@/util/common.js'

export default {
    name: 'ServiceListItem',
    data(){
        return{
            isPay :false,
            color : "#A6A6A6"
        }
    },
    props: {
        service: Object
    },
    components:{
        Badge,
        Button
    },
    computed: {
        getImgUrl() {
            return `${HTTP.IMG_URL}/${this.service.imgurl}`
        },
        formattedPrice() {
            return Common.toNumberFormat(this.service.price)
        }
    },methods:{
        pay(){
            if(this.service.cancelcheck==true){
                 axios.delete(`${HTTP.BASE_URL}/pay/${this.service.payno}`)
                 .then(()=>{
                     alert('환불성공')
                     this.$router.go()
                 }) 
                 .catch(err => {
                console.log(err)
            }) 
            }else{
                document.getElementById('returnpay')
            }
        },
        changeDetail(){
            this.$router.push(`/services/${this.service.servno}`)
        }
    },
    created(){
        if(this.$route.path=='/paylist')
        this.isPay=!this.isPay
    },
    
}
</script>

<style>
    #pdate{
        float:right;
    }
    .search-result-card {
        display: inline-block;
        padding: 5px 10px 5px 10px;
    }
    .button-class Button{
        float:right;
    }
    #btn_1{
        opacity: 0.6;
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
