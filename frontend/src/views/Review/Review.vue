<template>
  <div class="ReiewCreate container">
    <form id ="review">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title">
        </div>
        <div class="form-group">
            <label for="content" style="display:block">내용</label>
            <textarea name ="content" form="review" id="content"></textarea>
        </div>
           <div class="form-group">
            <label for="Point">평점</label>
            <select  class="form-control" id="Point">
                <option>5</option>
                <option>4</option>
                <option>3</option>
                <option>2</option>
                <option>1</option>
            </select>
        </div>
    </form>
    <div id="btn-group">
      <Button  @click="onCreate" buttonText="확인"/>
      <Button id="cancleB" @click="onChangePage" buttonText="취소" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Button from '@/components/common/Button.vue';
// @ is an alias to /src
export default {
  name: 'Review',
  components: {
    Button
    
  },
  data: function() {
    return {
      PayInfo: [],
      title: "",
      content: ""
    };
},methods: {
    created() {
    axios
      .get("/api/paylist")
      .then(({ data }) => {
        console.dir(data);
        if(data==null){
          alert("권한이 없습니다.");
          this.onChangePage();
        }
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  },
    onCreate() {
      axios
        .post("/api/review", {
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
#cancleB{
  background-color: #343a40;
  color : white;
  border : 1px solid black;
  opacity: 70%;
}
#btn-group{
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  
}
.ReiewCreate{
  height:90vh;
}
textarea{
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


