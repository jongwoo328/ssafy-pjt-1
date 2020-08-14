<template>
  <div class="container" id="qna">
    <GoBack/>
    <h2 class="font-notojp">Q&A</h2>
    <hr>
    <div class="qna-title">
      <h3>내 질문 목록</h3>
      <div>
        <router-link to='/qna/create'>작성</router-link>
      </div>
    </div>
    <Table :rowList="qnaList" />
  </div>
</template>

<script>
import Table from '@/components/qna/Table.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import Time from '@/util/common.js'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name:'Qna',
    components: {
      Table,
      GoBack
    },
    data() {
      return {
        qnaList: []
      }
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
      this.$emit('sidebar')
      if (this.$store.getters.isAdmin){
        axios.get(`${URL.BASE_URL}/qna`)
        .then(res => {
          let qnaData = res.data

          for (let i in qnaData){
            const now = new Date(Date.now())
            const date = new Date(qnaData[i].qdate)
  
            if (Time.isSameDay(now, date)) {
              qnaData[i].qdate = qnaData[i].qdate.split(' ')[1]
              
            } else {
              qnaData[i].qdate = qnaData[i].qdate.split(' ')[0].slice(2)
            }
          }
          this.qnaList = qnaData
        })
        .catch(err => console.log(err))

      } else {
        axios.get(`${URL.BASE_URL}/qna/${this.userNumber}`)
        .then(res => {
  
          let qnaData = res.data
  
          for (let i in qnaData){
            const now = new Date(Date.now())
            const date = new Date(qnaData[i].qdate)
  
            if (Time.isSameDay(now, date)) {
              qnaData[i].qdate = qnaData[i].qdate.split(' ')[1]
              
            } else {
              qnaData[i].qdate = qnaData[i].qdate.split(' ')[0].slice(2)
            }
          }
          this.qnaList = qnaData
        })
        .catch(err => console.log(err))
      }
    },
    computed: {
      userNumber() {
        return this.$store.getters.getUserData.userno
      }
    }
}
</script>

<style>
  #qna h2 {
    font-size: 2rem;
  }
  #qna h3 {
    display: inline-block;
    margin-bottom: 20px;
  }
  #qna .qna-title {
    margin-top: 60px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  #qna .qna-title div {
    position: relative;
    width: 100px;
  }
  #qna .qna-title a {
    position: absolute;
    right: 0;
    background-color: rgb(236,128,116);
    color: white;
    font-size: 1rem;
    padding: 5px 10px 5px 10px;
    
  }
  #qna .qna-title a:hover{
    text-decoration: none;
    background-color: rgb(214, 100, 88)
  }
</style>