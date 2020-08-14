<template>
  <div id="qna-detail" class="container">
    <GoBack/>
    <div class="title font-kor">
      <h3 class="mb-0" v-text="qnaData.qtitle"></h3>
    </div>
    <div class="info font-kor">
      <p class="font-kor name"><span class="qwriter" v-text="qnaData.qwriter" @click="toWriterProfile"></span></p>
      <div class="info-inside">
        <p class="d-inline-block" v-text="qnaData.qdate"></p>
        <div class="qna-ud">
          <a @click="deleteCheck = !deleteCheck">삭제</a>
          <a @click="acceptUpdate">수정</a>
        </div>
      </div>
      <div v-if="deleteCheck" class="delete-check">
        <p>정말 삭제하시겠습니까?</p>
        <div class="buttons">
          <Button buttonText="아니오" buttonColor="#343a40" @click.native="deleteNo"/>
          <Button buttonText="예" buttonColor="#e03131" @click.native="deleteYes"/>
        </div>
      </div>
    </div>
    <hr>
    <div v-if="updateCheck" class="update-form">
      <label for="qtitle" class="font-kor">제목</label>
      <input type="text" id="qtitle" class="font-kor">
      <Editor id="update-editor"/>
      <div class="buttons update-buttons">
        <Button buttonText="취소" buttonColor="#343a40" @click.native="updateNo"/>
        <Button buttonText="수정" buttonColor="rgb(236,128,116)" @click.native="updateYes"/>
      </div>
    </div>
    <div v-else class="content" v-html="qnaData.qcontent"></div>

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
          <Editor id="answer-editor" />
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
import GoBack from '@/components/common/GoBack.vue'

export default {
    name: 'QnaDetail',
    components: {
      Editor,
      Button,
      GoBack
    },
    data() {
      return {
        qnaData: {},
        deleteCheck: false,
        updateCheck: false,
      }
    },
    mounted() {
      this.$emit('sidebar')
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
      axios.get(`${URL.BASE_URL}/qna/detail/${this.qnaNumber}`)
      .then(res => {
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
        const reply = document.querySelector('#answer-editor .ql-editor').innerHTML
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
      deleteNo() {
        this.deleteCheck = false
      },
      deleteYes() {
        axios.delete(`${URL.BASE_URL}/qna/${this.qnaNumber}`)
        .then(res => {
          if (res.data === 'success') {
            this.$router.push({ name: 'Qna' })
          } else if (res.data === 'fail') {
            alert('전송에 실패했습니다.')
          } else {
            alert('알수없는 오류')
          }
        })
        .catch(err => console.log(err))
      },
      acceptUpdate() {
        this.updateCheck = true
        setTimeout(() => {
          const updateEditor = document.querySelector('.update-form .ql-editor')
          const qtitle = document.querySelector('#qtitle')

          updateEditor.innerHTML = this.qnaData.qcontent
          qtitle.value = this.qnaData.qtitle
        }, 10);
      },
      updateNo() {
        this.updateCheck = false
      },
      updateYes() {
        const updateContent = document.querySelector('#update-editor .ql-editor').innerHTML
        const updateTitle = document.querySelector('#qtitle').value
        const data = {
          qnano: this.qnaNumber,
          qtitle: updateTitle,
          qcontent: updateContent
        }
        axios.put(`${URL.BASE_URL}/qna/${this.qnaNumber}`, data, URL.JSON_HEADER)
        .then(res => {
          if (res.data === 'success') {
            this.$router.go()
          } else if (res.data === 'fail') {
            alert('수정되지 않았습니다')
          } else {
            alert('알수없는 오류')
          }
        })
        .catch(err => {
          console.log(err)
        })
      },
      toWriterProfile() {
        this.$router.push(`/accounts/${this.qnaData.qwriter}`)
      },
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
  #qna-detail .info {
    width: 100%;
    position: relative;
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
    /* float: right; */
  }
  #qna-detail .answer-content {
    min-height: 200px;
  }
  #qna-detail .delete {
    text-align: right;
  }
  #qna-detail .qna-ud a{
    margin: 0 5px 0 5px;
  }
  #qna-detail .info-inside a:hover {
    color: rgb(236,128,116);
    cursor: pointer;
  }
  #qna-detail .info-inside {
    display: flex;
    justify-content: space-between;
  }
  #qna-detail .delete-check {
    width: 200px;
    height: 100px;
    position: absolute;
    /* border: 1px solid black; */
    box-shadow: 0 1px 5px gray;
    border-radius: 7px;
    right: 0;
    top: 70px;
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    z-index: 5;
  }
  #qna-detail .delete-check p {
    margin: 0;
  }
  #qna-detail .buttons Button {
    margin-left: 5px;
    margin-right: 5px;
  }
  #qna-detail .update-buttons {
    display: flex;
    flex-direction: row;
    justify-content:flex-end;
  }
  #qna-detail .update-buttons button {
    display: inline-block;
  }
  #qna-detail .admin button {
    float: right;
  }
  #qna-detail .update-form label{
    display: block;
  }
  #qna-detail .update-form input {
    display: block;
    width: 100%;
    margin-bottom: 15px;
  }
  #qna-detail .qwriter:hover {
    cursor: pointer;
    font-size: 1.3rem;
  }
  @media (min-width: 768px) {
    #qna-detail .delete-check {
      width: 300px;
    }
  }
</style>