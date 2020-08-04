<template>
  <div id="service-detail" class="container font-kor">
      <div class="service-info section">
          <div class="image-join">
              <img src="@/assets/logo_mini.png" alt="">
          </div>
          <div class="info">
              <div>
                <p class="mobile"><i class="fas fa-won-sign"></i>가격</p>
                <p class="mobile"><i class="far fa-smile"></i>평점</p>
                <p class="mobile"><i class="fas fa-map-marker-alt"></i>위치</p>
                <h1>{{serviceData.servname}}</h1>
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
              <Button buttonText="문의하기" />
              <Button buttonText="신청하기" />
          </div>
      </div>
      <div class="service-desc section">
          <h2>설명</h2>
          <hr>
          <p v-html="serviceData.description"> </p>
      </div>
      <div class="review section">
          <h2>리뷰</h2>
          <hr>
          <ReviewList/>
      </div>
  </div>
</template>

<script>
import Button from '@/components/common/Button.vue'
import ReviewList from '@/components/service/ReviewList.vue'
import HTTP from "@/util/http-common.js"
import axios from 'axios'

export default {
    name: 'ServiceDetail',
    data() {
        return {
            userno: "",
            serviceId: 0,
            serviceData: {
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
            addr: "",
            review: [],
            point : "",
        }
    },
    components: {
        Button,
        ReviewList,
    },
    created() {
        if(this.$store.getters.getUserData === null){
            this.userno = 0;    
        } else{
            this.userno = `${this.$store.getters.getUserData.userno}`
        }       
        // const formData = new FormData()
        //     formData.append('servno', `${this.$route.params.service_id}`)
        //     formData.append('userno', this.userno)
        //      for (let key of formData.entries())
        //     {
        //     console.log(`${key}`)
        //     }
    
        axios.get(`${HTTP.BASE_URL}/service/detail/servno=${this.$route.params.service_id}&userno=${this.userno}`)
        .then(res =>{
            console.log(res)
            this.serviceData ={
                imgUrl:`${URL.BASE_URL}/` + res.data.imgUrl,
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
        })
        .catch(err => {
                console.log(err)
        })
        axios.get(`${HTTP.BASE_URL}/review/${this.$route.params.service_id}`,HTTP.JSON_HEADER)
        .then(res => {
            console.log(res);
            for(let review in res.data){
                this.review.push({
                   "title" : res.data[review]["title"],
                   "content" : res.data[review]["content"],
                   "point" : res.data[review]["point"],
                   "writer" : res.data[review]["writer"]
                })
          
            }
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
    .service-info {
        display: block;
    }
    #service-detail img {
        object-fit: contain;
        max-width: 100%;
        min-height: 200px;
        display: block;
        margin: auto;
        /* width: 350px;
        height: 350px; */
    }
    #service-detail h1, #service-detail h2 {
        font-size: 1.5rem;
    }
    #service-detail .image-join{
        display: block;
        text-align: center;
        padding: 30px 0 30px 0;
    }
    /* #service-detail .price {
        text-align: center;
        margin: 30px 0 30px 0;
        font-size: 2rem;
    } */
    #service-detail button {
        width: 100%;
    }
    #service-detail .web {
        display: none;
    }
    .info h1 {
        text-align: center;
    }
    .section {
        margin-bottom: 50px;
    }
    .info i {
        width: 30px;
    }
    @media (min-width: 768px) {
        #service-detail .web {
            display: block;
        }
        #service-detail .mobile {
            display: none;
        }
        .service-info {
            display: flex;
        }
        .image-join {
            width: 50%;
        }
        .info {
            width: 50%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        #service-detail button { 
            display: block;                                                                                                                                                                                            
            width: 100%;
            margin: 0 auto 0 auto;
            text-align: center;
        }
        span.label {
            float: left;
        }
        .web p {
            text-align: right;
        }
    }
    @media (min-width: 992px) {
        #service-detail button {
            width: 50%;
        }
        #service-detail h1, #service-detail h2 {
            font-size: 2rem;
        }
    }
</style>