<template>
  <div id="service-detail" class="container font-kor">
      <MessageModal v-if="messageModal" :recivername="proname" :Sendtype="sendtype" @close ="msgShow" />
      <PayModal v-if="payModal" :servicedataModal="serviceData" @close="payShow"/>
      <div class="service-info section">
          <div class="image-join">
              <transition>
                <img v-show="isLoaded" :src="serviceData.imgUrl" alt="" @load="onImgLoad">
              </transition>
              <img v-show="!isLoaded" src="@/assets/loading2.gif" alt="loading...">
          </div>
          <div class="info">
              <div class="sinfo">
                <div class="mobile">
                    <i class="fas fa-won-sign"></i>
                    <div class="container-fluid">
                        <span v-text="formattedPrice"></span>원
                    </div>
                </div>
                <div class="mobile">
                    <i class="far fa-smile"></i>
                    <div class="container-fluid">
                        <span v-text="point"></span>점
                    </div>
                </div>
                <div class="mobile">
                    <i class="fas fa-map-marker-alt"></i>
                    <div class="container-fluid">
                        <span v-text="addr"></span>
                    </div>
                </div>
                <h1 class="title">{{serviceData.servname}}</h1>
                <hr>
                <div class="web price">
                    <span class="label"><i class="fas fa-won-sign"></i> 가격</span>
                    <p><span>{{serviceData.price}}</span>원</p>
                </div>
                <div class="web rating">
                    <span class="label"><i class="far fa-smile"></i> 평점</span>
                    <p><span>{{point}}</span>점</p>
                </div>
                <div class="web addr">
                    <span class="label"><i class="fas fa-map-marker-alt"></i> 위치</span>
                    <p>{{addr}}</p>
                </div>
              </div>
            <div v-if="!isOwner">
            <div class="buttons">
              <Button @click.native="msgShow" buttonText="문의하기" />
              <Button v-if="!serviceData.payed" @click.native="payShow" buttonText="신청하기" />
              <Button v-else buttonText="리뷰작성" @click.native="createReview"/>
            </div>
            </div>
            <div class="buttons" v-else>
                <Button buttonText="수정" @click.native="onchangePage" />
              <Button buttonText="삭제" @click.native=" removeService "/>
            </div>
          </div>
      </div>
      <div class="service-desc section">
          <h2>설명</h2>
          <hr>
          <p v-html="serviceData.description"> </p>
      </div>
      <div class="review section">
          <div class="review-section">
            <h2>리뷰</h2>
            <a v-if="serviceData.payed" @click="createReview">리뷰 작성</a>
          </div>
          <hr>
          <ReviewList :reviews="review"/>
      </div>
  </div>
</template>

<script>
import Button from '@/components/common/Button.vue'
import ReviewList from '@/components/service/ReviewList.vue'
import HTTP from "@/util/http-common.js"
import axios from 'axios'
import MessageModal from '@/components/modal/MessageModal.vue'
import PayModal from '@/components/modal/PayModal.vue'
import Common from '@/util/common.js'

export default {
    name: 'ServiceDetail',
    props: {
        recivername: String,
        Sendtype: Number,
        servicedataModal: Object
    },
    data() {
        return {
            messageModal: false,
            payModal:false,
            isOwner:false,
            isLoaded: false,
            proname: "",
            sendtype: "",
            userno: "",
            servno: "",
            serviceId: 0,
            serviceData: {
                username:"",
                userno:"",
                servno:"",
                servname :"",
                price : "",
                saddr1 : "",
                saddr2 : "",
                saddr3 : "",
                saddr4 : "",
                saddr5 : "",
                saddr6 : "",
                imgUrl : "",
                description : "",
                payed :"",
            },
            payed :"",
            addr: "",
            review: [],
            point : "",
            revwrite: null,
        }
    },
    computed: {
        formattedPrice() {
            return Common.toNumberFormat(this.serviceData.price)
        }
    },
    components: {
        Button,
        ReviewList,
        MessageModal,
        PayModal,
    },
    methods:{
        onImgLoad(){
            this.isLoaded = true
        },
        payShow(){
            this.payModal = !this.payModal
            if(!this.payModal){
                this.$router.go()
            }
       },
        msgShow(){
            this.messageModal = !this.messageModal
            this.sendtype = 1   
        },
        onchangePage(){
            this.$router.push(`/services/${this.$route.params.service_id}/modify`)
        },
        removeService(){
            axios.delete(`${HTTP.BASE_URL}/service/${this.$route.params.service_id}`)
            .then(res =>{
                console.log(res)
                console.log("삭제성공")
                this.$router.push(`/myservice`)
            })
            .catch(err => {
                console.log(err)
            })
        },
        createReview() {
            console.log(typeof this.review.revwrite)
            if (this.revwrite) {
                alert('이미 작성하신 리뷰가 있습니다.')
            } else {
                this.$router.push(`/services/${this.$route.params.service_id}/review/create`)
            }
        }
    },
    mounted() {
        this.$emit('sidebar')
    },
    created() {
        if(this.$store.getters.getUserData === null){
            this.userno = 0;    
        } else{
            this.userno = `${this.$store.getters.getUserData.userno}`
        }       
        axios.get(`${HTTP.BASE_URL}/service/detail/servno=${this.$route.params.service_id}&userno=${this.userno}`)
        .then(res =>{
            console.log(res)
            this.revwrite = res.data.revwrite
            this.proname = res.data.proname,
            this.serviceData ={
                username : res.data.proname,
                userno : res.data.userno,
                servno : res.data.servno,
                imgUrl:`${HTTP.BASE_URL}/` + res.data.imgurl,
                servname : res.data.servname,
                price : res.data.price,
                saddr1 : res.data.saddr1,
                saddr2 : res.data.saddr2,
                saddr3 : res.data.saddr3,
                saddr4 : res.data.saddr4,
                saddr5 : res.data.saddr5,
                saddr6 : res.data.saddr6,
                description : res.data.description,
                payed : res.data.revcheck
            }
           
            this.point = res.data.avgpoint,
            this.addr = this.serviceData.saddr2+" "+this.serviceData.saddr4+" "+this.serviceData.saddr6
            if(this.userno==res.data.userno){
                this.isOwner=true;
            }
        })
        .catch(err => {
                console.log(err)
        })
        axios.get(`${HTTP.BASE_URL}/review/${this.$route.params.service_id}`,HTTP.JSON_HEADER)
        .then(res => {
            console.log(res);
            this.review = res.data 
        })
        .catch(err => {
                console.log(err)
        })
    }
}
</script>

<style>
    #service-detail {
        display: block;
        margin-top: 50px;
        min-height: 600px;
        padding: 20px;
        /* border: 1px solid black; */
    }
    #service-detail .service-info {
        display: block;
    }
    #service-detail .image-join img {
        object-fit: cover;
        max-width: 100%;
        max-height: 200px;
        display: block;
        margin: auto;
        /* width: 350px;
        height: 350px; */
    }
    #service-detail .review-section {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items:flex-end;
    }
    #service-detail .review-section a:hover {
        color: rgb(236,128,116);
        cursor: pointer;
    }
    #service-detail h1, #service-detail h2 {
        font-size: 1.5rem;
    }
    #service-detail .image-join{
        display: flex;
        text-align: center;
        border: 1px solid gray;
        max-height: 200px;
        min-height: 200px;
    }
    /* #service-detail .price {
        text-align: center;
        margin: 30px 0 30px 0;
        font-size: 2rem;
    } */
    #service-detail .info button {
        width: 100%;
    }
    #service-detail .web {
        display: none;
        margin-top: 30px;
    }
    #service-detail .info h1 {
        text-align: center;
    }
    #service-detail .section {
        margin-bottom: 50px;
    }
    #service-detail .mobile {
        margin: 30px 0 10px 0;
        padding: 0 50px 0 50px;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: flex-start;
    }
    #service-detail .mobile > div {
        text-align: center;
    }
    #service-detail .info .buttons button {
        margin: 10px 0 10px 0;
    }
    #service-detail h1 {
        margin-top: 40px;
    }
    #service-detail .info i {
        width: 30px;
    }
    @media (min-width: 768px) {
        #service-detail .web {
            display: block;
        }
        #service-detail .mobile {
            display: none;
        }
        
        #service-detail .service-info {
            display: flex;
        }
        #service-detail .image-join {
            width: 50%;
            max-height: 450px;
        }
        #service-detail .image-join img {
            max-height: 100%;
        }
        #service-detail .info {
            padding: 30px 60px 30px 60px;
            width: 50%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        #service-detail .info .buttons {
            padding: 10px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }
        #service-detail .info button { 
            display: block;                                                                                                                                                                                            
            width: 100%;
            margin: 20px auto 20px auto;
            text-align: center;
        }
        #service-detail span.label {
            float: left;
        }
        #service-detail .web p {
            text-align: right;
        }
    }
    @media (min-width: 992px) {
        #service-detail .info button {
            width: 45%;
        }
        #service-detail h1, #service-detail h2 {
            font-size: 2rem;
        }
    }
</style>
