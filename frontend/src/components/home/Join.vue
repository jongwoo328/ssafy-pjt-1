<template>
  <div id="join">
          <input type="text" class="font-kor" @keyup.enter="searchInfoemit" v-model="keyword" :placeholder="placeHolder"><Button buttonText="검색" @click.native="searchInfoemit"/>
      <router-link v-if="!isLoggedIn" class="font-kor" to='/accounts/signup' id="button-join">지금 가입하기</router-link>
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
            keyword: "",
            placeHolder: '하고싶은 것을 검색하세요',
            search:{}
        }
    },
    computed: {
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        }
    },
    methods:{
        searchInfoemit(){
            this.search.keyword=this.keyword
            this.$emit("child",this.search)
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
    #button-join:hover, #join button:hover {
        text-decoration: none;
        box-shadow: 0 1px 5px gray;
    }
    @media (min-width: 768px) {
        #join {
            display: none;
        }

    }
</style>