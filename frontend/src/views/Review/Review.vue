<template>
  <div class="ReiewCreate container font-kor">
    <GoBack/>
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" v-model="title">
        </div>
        <div class ="form-content">
            <label for="contents" style="display:block">내용</label>
            <textarea name="content" form="review" id="contents" v-model="content" ></textarea>
        </div>
           <div class="form-group">
              <label>평점</label>
              <star-rating v-model="rating" :star-size="starsize" :show-rating="temp"></star-rating>
           </div>
    <div id="btn-group">
      <Button  @click.native="onCreate" buttonText="확인"/>
      <Button id="cancleB" type="reset" buttonText="취소" />
    </div>
  </div>
</template>

<script>
import axios from "axios"
import Button from '@/components/common/Button.vue'
import StarRating from 'vue-star-rating'
import HTTP from "@/util/http-common.js"
import GoBack from '@/components/common/GoBack.vue'

export default {
  name: 'Review',
  components: {
    Button,
    StarRating,
    GoBack
  },
  data: function() {
    return {
      title: "",
      content: "",
      rating: null,
      temp:false,
      starsize : 50
    };
},
created() {
  this.$emit('sidebar')
},
methods: {
    onCreate() {
      let reviewData ={
        'servno' : `${this.$route.params.service_id}`,
        'userno' : this.$store.getters.getUserData.userno,
        'title' : this.title,
        'content' : this.content,
        'point' : this.rating
      }

      axios.post(`${HTTP.BASE_URL}/review`,reviewData)
        .then(() => {
          alert("등록 성공");
          this.onChangePage();
        })
        .catch(err =>{
          console.log(err)
        })
    },
    onChangePage() {
      this.$router.push(`/services/${this.$route.params.service_id}`);
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
  margin-top :50px;
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


