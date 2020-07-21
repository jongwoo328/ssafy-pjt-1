<template>
  <div id="join">
      <form action="">
          <input type="text" class="font-kor" :placeholder="placeHolder"><Button buttonText="검색" />
      </form>
      <router-link class="font-kor" to='/accounts/signup' id="button-join">지금 가입하기</router-link>
  </div>
</template>

<script>
import Button from '@/components/common/Button.vue'
export default {
    name: 'Join',
    components: {
        Button
    },
    data() {
        return {
            placeHolder: '하고싶은 것을 검색하세요'
        }
    }
}
</script>

<style scoped>
    p {
        margin-bottom: 5px;
    }
    #join {
        padding: 30px 0 30px 0;
    }
    h2 {
        margin-bottom: 25px;
    }
    form {
        padding: 0;
        margin: 0;
    }
    input {
        width: 100%;
        height: 50px;
        line-height: 50px;
        padding-left: 10px;
        margin-bottom: 5px;
        border: 1px solid rgb(54, 54, 54);
        border-radius: 7px;
    }
    button {
        width: 100%;
        margin-top: 15px;
        margin-bottom: 20px;
    }
    #button-join {
        display: block;
        text-align: center;
        font-size: 2rem;
        color: rgb(236,128,116);
        padding: 10px;
        background-color: white;
        border-radius: 10px;
    }
    #button-join:hover {
        text-decoration: none;
        box-shadow: 0 1px 5px gray;
    }
</style>