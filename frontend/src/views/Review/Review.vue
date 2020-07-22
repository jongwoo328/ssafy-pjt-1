<template>
  <div class="ReiewCreate container font-kor">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title">
        </div>
        <div class ="form-content">
            <label for="contents" style="display:block">내용</label>
            <textarea name ="content" form="review" id="contents" ></textarea>
        </div>
           <div class="form-group">
              <label>평점</label>
              <star-rating v-model="rating" star-size="60" :show-rating="temp"></star-rating>
           </div>
    <div id="btn-group">
      <Button  @click="onCreate" buttonText="확인"/>
      <Button id="cancleB" @click="onChangePage" buttonText="취소" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Button from '@/components/common/Button.vue';
import StarRating from 'vue-star-rating'


export default {
  name: 'Review',
  components: {
    Button,
    StarRating
    
  },
  data: function() {
    return {
      PayInfo: [],
      s_no: "",
      title: "",
      content: "",
      rating:"",
      temp:false
    };
},methods: {
  //   created() {
  //   axios
  //     .get("/api/paylist")
  //     .then(({ data }) => {
  //       console.dir(data);
  //       if(data==null){
  //         alert("권한이 없습니다.");
  //         this.onChangePage();
  //       }
  //     })
  //     .catch(() => {
  //       alert("에러가 발생했습니다.");
  //     });
  // },
    onCreate() {
      axios
        .post("/api/qna", {
          // Userid: this.$session.get("jwt").id,
          ReviewTitle: this.title,
          ReviewContent: this.content
        })
        .then(res => {
          alert("등록 성공");
          console.log(res);
          this.onChangePage();
        });
    },
    onChangePage() {
      this.$router.push("/Home");
    }
  }
};


</script> 

<style scoped>

.font-kor{
  font-size :1.5rem;
}

#cancleB{
  background-color: #343a40;
  color : white;
  border : 1px solid black;
}
#btn-group{
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  
}
.ReiewCreate{
  margin-top :10px;
  height:70vh;
}
textarea{
  margin-top: 0;
  width: 100%;
  height:  300px;
   box-shadow: 0 0 5px gray;
}
/* form {
   border: 2px solid black; 
} */
input{
    box-shadow: 0 0 5px gray;
}

</style>>


