<template>
  <div id="service-detail" class="container font-kor">
      <div class="service-info section">
          <div class="image-join">
              <img src="@/assets/logo_mini.jpg" alt="">
          </div>
          <div class="info">
              <div>
                <p class="mobile"><i class="fas fa-won-sign"></i>가격</p>
                <p class="mobile"><i class="far fa-smile"></i>평점</p>
                <p class="mobile"><i class="fas fa-map-marker-alt"></i>위치</p>
                <h1>서비스 이름</h1>
                <hr>
                <div class="web price">
                    <span class="label"><i class="fas fa-won-sign"></i> 가격</span>
                    <p><span>1000</span>원</p>
                </div>
                <div class="web rating">
                    <span class="label"><i class="far fa-smile"></i> 평점</span>
                    <p><span>5</span>점</p>
                </div>
                <div class="web addr">
                    <span class="label"><i class="fas fa-map-marker-alt"></i> 위치</span>
                    <p>주소 주소 주소</p>
                </div>
              </div>
              <Button buttonText="신청하기" />
          </div>
      </div>
      <div class="service-desc section">
          <h2>설명</h2>
          <hr>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Assumenda fugiat ex modi sed totam repellendus corporis voluptates possimus. Animi magni non, omnis maiores iure fugit temporibus necessitatibus debitis adipisci expedita!
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quaerat tempora odit facere laborum maiores veritatis. Dicta itaque molestiae nobis sequi consequatur? Autem aliquam quaerat dolorem iusto quis, ipsam a at!
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quam delectus recusandae dolorum sapiente, fugit optio. Quod, numquam molestiae enim fugit officia quidem veniam tempore dolor tenetur laborum vel, iste fuga.
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, necessitatibus quo quod aspernatur error modi maiores soluta accusantium nesciunt tempora dolorem debitis natus iure, perferendis ipsa iusto! Odit, deserunt reprehenderit.
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti velit blanditiis veniam vero porro eius recusandae quos est tempore, consectetur molestiae, commodi, cupiditate beatae ea. Rerum saepe quasi repudiandae labore?
          </p>
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

export default {
    name: 'ServiceDetail',
    data() {
        return {
            serviceId: 0,
            serviceData: {},

        }
    },
    components: {
        Button,
        ReviewList,
    },
    created() {
        
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