<template>
  <div id="qna-detail" class="container">
    <div class="title font-kor">
      <h3 class="mb-0" v-text="qnaData.qtitle"></h3>
    </div>
    <div class="date font-kor">
      <p v-text="qnaData.qdate"></p>
    </div>
    <hr>
    <div class="content" v-html="qnaData.qcontent"></div>
    <hr>
    <div class="comments">
      <h3>답변</h3>
      <div v-if="isAdmin" class="admin">
      </div>
      <div v-else class="user">
        <div v-if="!hasReply" class="reply">
        </div>
        <div v-else class="no-reply">
          <p>답변이 없습니다</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    name: 'QnaDetail',
    data() {
      return {
        qnaData: {

        },
      }
    },
    created() {
      axios.get(`${URL.BASE_URL}/qna/detail/${this.qnaNumber}`)
      .then(res => {
        console.log(res)
        this.qnaData = res.data
      })
      .catch(err => console.log(err))
    },
    computed: {
      qnaNumber() {
        return this.$route.params.q_no
      },
      isAdmin() {
        return this.$store.getters.getUserData
      }
    },
    methods: {
      hasReply() {
        if (this.qnaData.acontent === null) {
          return true
        }
        return false
      }
    },

}
</script>

<style>
  #qna-detail {
    margin-top: 50px;
  }
  #qna-detail .title {
    display: flex;
    flex-direction: row;
    margin: 50px 0 50px 0;
  }
  #qna-detail h3 {
    font-size: 2rem;
  }
  #qna-detail .comments h3 {
    font-size: 2rem;
  }
</style>