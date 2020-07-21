<template>
  <div class="navbar-nav">
      <NavbarLinkListItem value="SignUp" />
      <NavbarLinkListItem value="Login" @click.native="changeLogin"/>
      <LoginModal v-if="loginModal" @close="changeLogin">
      </LoginModal>
  </div>
</template>

<script>
import NavbarLinkListItem from '@/components/nav/NavbarLinkListItem.vue'
import LoginModal from '@/components/modal/LoginModal.vue'

export default {
  name: 'NavbarLinkList',
  data() {
    return {
      loginModal: false
    }
  },
  components: {
    NavbarLinkListItem,
    LoginModal,
  },
  methods: {
    changeLogin(){
      console.log(this.loginModal)
      this.loginModal = !this.loginModal
      console.log(this.loginModal)
    }
  },
}
</script>

<style>
  .navbar-nav {
    display: flex;
    flex-direction: row;
  }
</style>