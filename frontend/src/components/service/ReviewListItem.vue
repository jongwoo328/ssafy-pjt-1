<template>
  <div class="review-list-item">
      <div class="header">
          <div class="review-info">
            <div class="profile-image">
                <img :src="imgurl" alt="">
            </div>
            <div class="name-rating">
                <p class="review-name" v-text="review.writer"></p>
                <p class="rating" v-text="star"></p>
            </div>
          </div>
          <div v-if="isMe" class="buttons conatiner-fluid">
            <div v-if="deleteCheck" class="delete-check">
                <p>정말 삭제하시겠습니까?</p>
                <div class="delete-buttons">
                <Button buttonText="아니오" buttonColor="#343a40" @click.native="deleteNo"/>
                <Button buttonText="예" buttonColor="#e03131" @click.native="deleteYes"/>
                </div>
            </div>
              <p class="mb-0 update-review">수정</p>
              <p @click="deleteCheck = !deleteCheck" class="mb-0 delete-review">삭제</p>
          </div>
      </div>
      <hr>
      <p v-text="review.title" class="review-title"></p>
      <p v-text="review.content"></p>
  </div>
</template>

<script>
import URL from '@/util/http-common.js'
import Button from '@/components/common/Button.vue'

export default {
    name: 'ReviewListItem',
    props:{
        review: Object
    },
    components: {
        Button
    },
    data() {
        return {
            isMe: false,
            deleteCheck: false,
        }
    },
    created() {
        const user = this.$store.getters.getUserData
        if (user && user.userno === this.review.userno) {
            this.isMe = true
        }
    },
    methods: {
        deleteNo() {
            this.deleteCheck = false
        },
        deleteYes() {
            // 
        }
    },
    computed:{
        star(){
            let result = ''
                for (let i=0; i<this.review.point; i++) {
                    result += '★'
                }
            for (let i=0; i<(5 - this.review.point); i++) {
                result += '☆'
            }
                return result
        },
        imgurl() {
            return `${URL.BASE_URL}/${this.review.imgurl}`
        }

    },
}
</script>

<style>
    .review-list-item {
        border: 1px solid black;
        border-radius: 5px;
        padding: 10px;
        margin: 20px 0 20px 0;
    }
    .review-list-item hr {
        margin: 5px 0 5px 0;
    }
    .review-list-item h3 {
        display: inline-block;
        font-size: 1.25rem;
    }
    .review-list-item p.rating {
        float: right;
        color: orangered;
    }
    .review-list-item .header {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        max-height: 60px;
    }
    .review-list-item .review-info {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .review-list-item .profile-image {
        width: 50px;
        height: 50px;
        max-height: 50px;
        max-width: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 50px;
        margin-right: 10px;
    }
    .review-list-item .header .buttons {
        display: flex;
        flex-direction: row;
        position: relative;
    }
    .review-list-item .header .buttons p {
        margin-left: 10px;
    }
    .review-list-item .header .buttons p:hover {
        cursor: pointer;
    }
    .review-list-item .header .buttons .delete-review {
        color: crimson;
    }
    .review-list-item .review-name {
        font-weight: bold;
    }
    .review-list-item .profile-image img {
        object-fit: scale-down;
        max-width: 100%;
        max-height: 100%;
    }
    .review-list-item .review-title {
        font-size: 1.2rem;
        font-weight: bold;
        margin-top: 10px;
    }
    .review-list-item .name-rating {
        height: 50%;
    }
    .review-list-item .name-rating p {
        margin: 0;
        vertical-align: middle;
    }
    .review-list-item .delete-check {
        width: 200px;
        height: 100px;
        position: absolute;
        /* border: 1px solid black; */
        box-shadow: 0 1px 5px gray;
        border-radius: 7px;
        right: 0;
        top: 0px;
        background-color: white;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        z-index: 5;
    }
    .review-list-item .delete-buttons button{
        margin: 0 10px 0 10px;
    }
    @media (min-width: 768px) {
        .review-list-item .delete-check {
        width: 300px;
        height: 120px;
        }
    }
</style>