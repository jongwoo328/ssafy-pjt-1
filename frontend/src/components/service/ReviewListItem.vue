<template>
  <div class="review-list-item">
      <div class="header">
          <div class="review-info">
            <div class="profile-image" @click="toProfile">
                <img :src="imgurl" alt="">
            </div>
            <div class="name-rating">
                <p class="review-name"><span v-text="review.writer" @click="toProfile"></span></p>
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
              <p class="mb-0 update-review" @click="updateCheck = !updateCheck">수정</p>
              <p @click="deleteCheck = !deleteCheck" class="mb-0 delete-review">삭제</p>
          </div>
      </div>
      <hr>
      <div class="review-update-form" v-if="updateCheck">
          <label for="revtitle">제목</label>
          <StarRating v-model="updateForm.point" :star-size="starsize" :show-rating="F" />
          <input v-model="updateForm.title" type="text" name="revtitle" id="review-title">
          <label for="revcontent">내용</label>
          <textarea v-model="updateForm.content" name="revcontent" id="review-content"></textarea>
          <div>
            <Button @click.native="putReview" buttonText="수정"/>
          </div>
      </div>
      <div v-else>
        <p v-text="review.title" class="review-title"></p>
        <p v-text="review.content"></p>
      </div>
  </div>
</template>

<script>
import URL from '@/util/http-common.js'
import Button from '@/components/common/Button.vue'
import axios from 'axios'
import StarRating from 'vue-star-rating'

export default {
    name: 'ReviewListItem',
    props:{
        review: Object
    },
    components: {
        Button,
        StarRating
    },
    watch: {
        updateCheck() {
            this.updateForm.title = this.review.title
            this.updateForm.content = this.review.content
            this.updateForm.point = this.review.point
            this.updateForm.revno = this.review.revno
        }
    },
    data() {
        return {
            isMe: false,
            deleteCheck: false,
            updateCheck: false,
            F: false,
            starsize: 20,
            updateForm: {
                content: "",
                point: 0,
                revno: 0,
                title: "",
            }
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
            axios.delete(`${URL.BASE_URL}/review/${this.review.revno}`)
            .then(res => {
                if (res.data == 'success') {
                    alert('삭제되었습니다.')
                    this.$router.go()
                } else {
                    alert('전송에 실패했습니다.')
                }
            })
            .catch(err => {
                console.log(err)
            })
        },
        putReview() {
            axios.put(`${URL.BASE_URL}/review/${this.review.revno}`, this.updateForm)
            .then(res => {
                if (res.data == 'success') {
                    alert('수정되었습니다.')
                    this.$router.go()
                } else {
                    alert('전송에 실패했습니다.')
                }
            })
        },
        toProfile() {
            this.$router.push(`/accounts/${this.review.writer}`)
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
            return `${URL.IMG_URL}/${this.review.imgurl}`
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
        object-fit: cover;
        width: 100%;
        height: 100%;
        border-radius: 50%;
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
    .review-list-item .review-update-form {
        position: relative;
    }
    .review-list-item .review-update-form .vue-star-rating {
        position: absolute;
        right: 0;
        top: 0;
    }
    .review-list-item .review-update-form input, 
    .review-list-item .review-update-form label,
    .review-list-item .review-update-form textarea {
        display: block;
        width: 100%;
    }
    .review-list-item .review-update-form textarea {
        height: 75px;
        resize: none;
    }
    .review-list-item .review-update-form button {
        margin-top: 10px;
    }
    .review-list-item .review-update-form > div {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
    }
    .review-list-item .profile-image:hover {
        cursor: pointer;
    }
    .review-list-item .review-name span:hover {
        cursor: pointer;
        font-size: 1.1rem;
    }
    @media (min-width: 768px) {
        .review-list-item .delete-check {
        width: 300px;
        height: 120px;
        }
    }
</style>