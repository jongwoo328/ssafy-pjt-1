<template>
  <div class="container" id="qna">
    <h2 class="font-notojp">Q&A</h2>
    <hr>
    <div class="qna-title">
      <h3>내 질문 목록</h3>
      <div>
        <router-link to='/test'>작성</router-link>
      </div>
    </div>
    <Table :rowList="qnaList" />
  </div>
</template>

<script>
import Table from '@/components/common/Table.vue'

export default {
    name:'Qna',
    components: {
      Table
    },
    data() {
      return {
        qnaList: [
          {
            q_no: 1,
            q_title: '질문있습니다asdfasdfasdfasdfasdf',
            q_content: '내용',
            q_date: '날짜날짜',
            u_no: 3,
            a_writer: '답변자',
            a_content: '답변내용',
            a_date: '답변날짜'
          },
          {
            q_no: 1,
            q_title: '질문있습니다',
            q_content: '내용',
            q_date: '날짜날짜',
            u_no: 3,
            a_writer: '답변자',
            a_content: '답변내용',
            a_date: '답변날짜'
          }
        ]
      }
    }
}
</script>

<style>
  #qna {
    padding-top: 50px;
  }
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