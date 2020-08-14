<template>
  <div class="ReiewCreate container">
    <GoBack/>
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
      <Button @click="onChangePage" buttonText="취소"/>
      <Button @click="onCreate" buttonText="수정"/>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Button from "@/components/common/Button.vue"
import URL from "@/util/http-common.js"
import GoBack from '@/components/common/GoBack.vue'

export default {
  name: 'Review',
  components: {
    Button,
    GoBack
  },
  data: function() {
    return {
      no:"",
      title: "",
      content: ""
    };
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
    axios
      .get(`${URL.BASE_URL}/review/${this.no}`)
      .then(({ data }) => {
        this.no = data.no;
        this.title = data.title;
        this.content = data.content;
        console.dir(data);
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  },
methods: {
    onCreate() {
      axios
        .post(`${URL.BASE_URL}/review`, {
          // Userid: this.$session.get("jwt").id,
          ReviewTitle: this.title,
          ReviewContent: this.content
        })
        .then(() => {
          alert("등록 성공");
          this.onChangePage();
        });
    },
    onChangePage() {
      this.$router.push(`/services/${this.no}`);
    }
  }
};
</script>
<style scoped>
#btn-group{
  display: flex;
  flex-direction: row-reverse;
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
ds   border: 2px solid black; 
} */
input{
    box-shadow: 0 0 5px gray;
}

</style>>


