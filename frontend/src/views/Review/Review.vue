<template>
  <div class="ReviewCreate">
    <form>
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title">
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <input type="text" class="form-control" id="content">
        </div>
           <div class="form-group">
            <label for="Point">평점</label>
            <select  class="form-control" id="Point">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
    </form>
      <button @click="onCreate">등록</button>
      <button @click="onChangePage">취소</button>
  </div>
</template>

<script>
import axios from "axios";
// @ is an alias to /src
export default {
  name: 'Review',
  components: {
    
  },
  data: function() {
    return {
      title: "",
      content: ""
    };
},methods: {
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

</style>>


