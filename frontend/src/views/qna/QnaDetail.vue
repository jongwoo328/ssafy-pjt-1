<template>
  <div id="qna-detail" class="container">
    <div class="title font-kor">
      <h3 class="mb-0" v-text="qnaData.qtitle"></h3>
    </div>
    <div class="info font-kor">
      <p class="font-kor name" v-text="qnaData.qwriter"></p>
      <p v-text="qnaData.qdate"></p>
    </div>
    <hr>
    <div class="content" v-html="qnaData.qcontent"></div>

    <hr class="division">

    <div class="answer">
      <div v-if="hasReply" class="reply">
        <div class="title font-kor">
          <h3 class="mb-0" v-text="atitle"></h3>
        </div>
        <div class="info font-kor">
          <p class="name" v-text="qnaData.awriter"></p>
          <p v-text="qnaData.adate"></p>
        </div>
        <hr>
        <div v-if="hasReply" class="answer-content" v-html="qnaData.acontent">
        </div>
      </div>
      
      <div v-else class="no-reply">
        <div v-if="!isAdmin" class="user">
          <p>답변이 없습니다</p>
        </div>
      </div>

      <div v-if="isAdmin" class="admin">
          <Editor/>
          <div class="d-block">
            <Button v-if="hasReply" buttonText="수정" @click.native="reply('update')"/>
            <Button v-else buttonText="작성" @click.native ="reply('create')"/>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import URL from '@/util/http-common.js'
import Editor from '@/components/common/Editor.vue'
import Button from '@/components/common/Button.vue'

export default {
    name: 'QnaDetail',
    components: {
      Editor,
      Button
    },
    data() {
      return {
        qnaData: {},
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
        return this.$store.getters.isAdmin
      },
      atitle() {
        return `Re: ${this.qnaData.qtitle}`
      },
      hasReply() {
        return this.qnaData.acontent !== null
      }
    },
    methods: {
      reply(command) {
        const reply = document.querySelector('.ql-editor').innerHTML
        const data = {
            acontent: reply,
            awriter: this.$store.getters.getUserData.name,
            qnano: this.qnaNumber
        }
        const CREATE_URL = `${URL.BASE_URL}/qna/answer/${this.qnaNumber}`
        const UPDATE_URL = `${URL.BASE_URL}/qna/answer/update/${this.qnaNumber}`
        if (command === 'create'){
          axios.put(CREATE_URL, data, URL.JSON_HEADER)
          .then(res => {
            console.log(res)
            if (res.data === 'success') {
              this.$router.go()
            } else if (res.data === 'fail') {
              alert('전송 오류.')
            }
          })
          .catch(err => console.log(err))
        } else if (command === 'update') {
          axios.put(UPDATE_URL, data, URL.JSON_HEADER)
          .then(res => {
            if (res.data === 'success') {
              this.$router.go()
            } else if (res.data === 'fail') {
              alert('전송 오류.')
            }
          })
          .catch(err => console.log(err))
        }
        
      },
      updateReply() {

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
    margin: 20px 0 20px 0;
  }
  #qna-detail h3 {
    font-size: 2rem;
  }
  #qna-detail .content {
    min-height: 300px;
  }
  #qna-detail .name {
    font-weight: bold;
    font-size: 1.25rem;
  }
  #qna-detail .division {
    height: 3px;
    border-top: 3px solid black;
  }
  #qna-detail .ql-editor {
    height: 300px;
  }
  #qna-detail Button {
    margin-top: 10px;
    float: right;
  }
  #qna-detail .answer-content {
    min-height: 200px;
  }
</style>