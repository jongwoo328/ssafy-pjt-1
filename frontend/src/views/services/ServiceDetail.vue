<template>
  <div id="service-detail" class="container font-kor">
      <GoBack/>
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
                <div class="mobile mobile-pro">
                    <i class="fas fa-user-circle"></i>
                    <div class="container-fluid">
                        <span @click="toProProfile" v-text="serviceData.username"></span>
                    </div>
                </div>
                <h1 class="title">{{serviceData.servname}}</h1>
                <hr>
                <div class="web price">
                    <span class="label"><i class="fas fa-won-sign"></i> 가격</span>
                    <p><span>{{formattedPrice}}</span>원</p>
                </div>
                <div class="web rating">
                    <span class="label"><i class="far fa-smile"></i> 평점</span>
                    <p><span>{{point}}</span>점</p>
                </div>
                <div class="web addr">
                    <span class="label"><i class="fas fa-map-marker-alt"></i> 위치</span>
                    <p>{{addr}}</p>
                </div>
                <div class="web web-pro">
                    <span class="label"><i class="fas fa-user-circle"></i> 프로</span>
                    <p @click="toProProfile">{{serviceData.username}}</p>
                </div>
              </div>
            <div v-if="!isOwner">
            <div class="w-buttons">
              <Button @click.native="msgShow" buttonText="문의하기" />
              <Button v-if="!serviceData.payed" @click.native="payShow" buttonText="신청하기" />
              <Button v-else buttonText="리뷰작성" @click.native="createReview"/>
            </div>
            </div>
            <div class="w-buttons" v-else>
                <Button buttonText="수정" @click.native="onchangePage" />
              <Button buttonText="삭제" @click.native="deleteCheck = !deleteCheck"/>
            </div>
            <div style="position: relative;">
            <div v-if="deleteCheck" class="delete-check">
                <p>정말 삭제하시겠습니까?</p>
                <div class="buttons">
                <Button buttonText="아니오" buttonColor="#343a40" @click.native="deleteNo"/>
                <Button buttonText="예" buttonColor="#e03131" @click.native="deleteYes"/>
                </div>
            </div>
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
            <div class="switch">
                <div class="review cursor-pointer" @click="showReview" style="">
                    <h2>리뷰</h2>
                    <div class="line" style="background-color: rgb(236,128,116); width = 100%"></div>
                </div>
                <div v-if="isOwner" class="user cursor-pointer" @click="showUser" style="border-left: none;">
                    <h2>참여자목록</h2>
                    <div class="line" style="width: 0px"></div>
                </div>
            </div>
            <a v-if="serviceData.payed" @click="createReview">리뷰 작성</a>
          </div>
          <hr>
          
          <ReviewList :reviews="review" v-if="displayreview"/>
          <UserList :userList="userData" :servename="serviceData.servname" :serveno="serviceData.servno" v-if="displayuser"/>
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
import UserList from '@/components/follow/UserList.vue'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name: 'ServiceDetail',
    props: {
        recivername: String,
        Sendtype: Number,
        servicedataModal: Object,
    },
    data() {
        return {
            deleteCheck: false,
            displayreview:true,
            displayuser:false,
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
            userData:{}
        }
    },
    computed: {
        formattedPrice() {
            return Common.toNumberFormat(this.serviceData.price)
        }
    },
    components: {
        UserList,
        Button,
        ReviewList,
        MessageModal,
        PayModal,
        GoBack
    },
    watch:{
         displayreview() {
            const reviewElement = document.querySelector('.switch .review .line')
            const userElement = document.querySelector('.switch .user .line')
            if (this.displayreview === true) {
                reviewElement.style.width = "100%"
                reviewElement.style.backgroundColor = "rgb(236,128,116)"
                userElement.style.width = "0px"
            } else {
                userElement.style.width = "100%"
                userElement.style.backgroundColor = "rgb(236,128,116)"
                reviewElement.style.width = "0px"
            }
        }
    },
    methods:{
        deleteNo() {
        this.deleteCheck = false
      },
      deleteYes() {
        this.removeService()
        .then(res => {
          if (res.data === 'success') {
            this.$router.push({ name: 'MyServices' })
          } else if (res.data === 'fail') {
            alert('전송에 실패했습니다.')
          } else {
            alert('알수없는 오류')
          }
        })
        .catch(err => console.log(err))
      },
         showReview() {
            this.displayreview = true
            this.displayuser = false
            axios.get(`${HTTP.BASE_URL}/review/${this.$route.params.service_id}`,HTTP.JSON_HEADER)
            .then(res => {
            this.review = res.data 
            })
        .catch(err => {
                console.log(err)
        })
        },
        showUser() {
            this.displayreview = false
            this.displayuser = true
        },
        onImgLoad(){
            this.isLoaded = true
        },
        payShow(){
            if (this.$store.getters.isLoggedIn) {

                this.payModal = !this.payModal
                if(!this.payModal){
                    this.$router.go()
                }
            } else {
                alert('로그인이 필요합니다')
            }
       },
        msgShow(){
            if (this.$store.getters.isLoggedIn) {
                this.messageModal = !this.messageModal
                this.sendtype = 1   
            } else {
                alert('로그인이 필요합니다')
            }
        },
        onchangePage(){
            this.$router.push(`/services/${this.$route.params.service_id}/modify`)
        },
        removeService(){
            axios.delete(`${HTTP.BASE_URL}/service/${this.$route.params.service_id}`)
            .then(() =>{
                this.$router.push(`/myservice`)
            })
            .catch(err => {
                console.log(err)
            })
        },
        createReview() {
            if (this.revwrite) {
                alert('이미 작성하신 리뷰가 있습니다.')
            } else {
                this.$router.push(`/services/${this.$route.params.service_id}/review/create`)
            }
        },
        toProProfile() {
            this.$router.push(`/accounts/${this.serviceData.username}`)
        }
    },
    mounted() {
        
    },
    created() {
        if(this.$store.getters.getUserData === null){
            this.userno = 0;    
        } else{
            this.userno = `${this.$store.getters.getUserData.userno}`
        }       
        axios.get(`${HTTP.BASE_URL}/service/detail/servno=${this.$route.params.service_id}&userno=${this.userno}`)
        .then(res =>{
            if (res.data === 'fail') {
                this.$router.push({
                name: 'Error',
                query: {
                    status: 404
                }
            })
            }
            this.revwrite = res.data.revwrite
            this.proname = res.data.proname,
            this.serviceData ={
                username : res.data.proname,
                userno : res.data.userno,
                servno : res.data.servno,
                imgUrl:`${HTTP.IMG_URL}/` + res.data.imgurl,
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
            this.$emit('sidebar')
        })
        .catch(err => {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 'unknown'
                }
            })
            console.log(err)
        })
        axios.get(`${HTTP.BASE_URL}/review/${this.$route.params.service_id}`,HTTP.JSON_HEADER)
        .then(res => {
            this.review = res.data 
        })
        .catch(err => {
                console.log(err)
        })
        axios.get(`${HTTP.BASE_URL}/account/payinfo/${this.$route.params.service_id}`)
        .then(res =>{
            this.userData=res.data
        })
        .catch(err =>{
            console.log(err)
        })
    }
}
</script>

<style>
#service-detail .line {
    height: 2px;
}
#service-detail .back {
    margin-bottom: 20px;
}
#service-detail .mobile-pro span:hover {
    cursor: pointer;
}
#service-detail .web-pro p {
    transition: font-size 0.2s ease;
}
#service-detail .web-pro p:hover {
    cursor: pointer;
    font-size: 1.1rem
}
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
        max-height: 198px;
        display: block;
        margin: auto;
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
    #service-detail .switch {
        display: flex;
        flex-direction: row;
    }
    #service-detail .switch > div{
        margin-right: 20px;
    }
    #service-detail .switch .line {
        transition: width 0.5s;
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
        margin-top: 20px;
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
        padding: 0 30px 0 30px;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: flex-start;
    }
    #service-detail .mobile > div {
        text-align: center;
    }
    #service-detail .info .w-buttons button {
        margin: 10px 0 10px 0;
    }
    #service-detail h1 {
        margin-top: 40px;
    }
    #service-detail .info i {
        width: 30px;
    }
    #service-detail .delete-check {
            width: 300px;
            height: 100px;
            position: absolute;
            /* border: 1px solid black; */
            box-shadow: 0 1px 5px gray;
            border-radius: 7px;
            right: 0;
            top: 0;
            background-color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            z-index: 5;
        }
    #service-detail .delete-check p {
        margin: 0;
    }
    #service-detail .delete-check .buttons{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        width: 100%;
        margin-top: 5px;
    }
    #service-detail .delete-check .buttons Button {
        margin-left: 5px;
        margin-right: 5px;
        width: 30%;
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
        #service-detail .info .w-buttons {
            padding: 10px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }
        #service-detail .info .button { 
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
        #service-detail .delete-check {
            width: 300px;
            margin: 0;
        }
        #service-detail .delete-check p {
            margin-top: 5px;
        }
        #service-detail .delete-check .buttons {
            margin: 0;
            height: fit-content;
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
