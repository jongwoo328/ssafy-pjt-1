<template>
  <div id="join">
      <h2 class="font-kor">
          <p>원하는</p>
          <p class="left-30">취미를</p>
          <p class="left-60">가져보세요.</p>
      </h2>
      <router-link class="font-kor" to='/accounts/signup' id="button-join">지금 가입하기</router-link>
  </div>
</template>

<script>
export default {
    name: 'Join',

}
</script>

<style scoped>
    p {
        margin-bottom: 5px;
    }
    #join {
        padding: 30px;
    }
    h2 {
        margin-bottom: 25px;
    }
    #button-join {
        display: block;
        text-align: center;
        font-size: 2rem;
        color: white;
        padding: 10px;
        background-color: rgb(236,128,116);
        border-radius: 10px;
    }
    #button-join:hover {
        text-decoration: none;
        color: rgb(54, 54, 54);
    }
</style>