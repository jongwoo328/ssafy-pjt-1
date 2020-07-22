<template>
  <div class="navbar-nav"> 
      <NoticeIcon/> 
      <NavbarLinkListItem v-if="!isLoggedIn" value="SignUp" @click.native="toSignUp" />
      <NavbarLinkListItem v-if="!isLoggedIn" value="Login" @click.native="changeLogin"/>
      <LoginModal v-if="loginModal" @close="changeLogin" @lost="changeModal">
      </LoginModal>
      <ForgotPasswordModal v-if="forgotPasswordModal" @close="changeForgot" @change="changeModal">
      </ForgotPasswordModal>
  </div>
</template>

<script>
import NavbarLinkListItem from '@/components/nav/NavbarLinkListItem.vue'
import LoginModal from '@/components/modal/LoginModal.vue'
import ForgotPasswordModal  from '@/components/modal/ForgotPasswordModal.vue'

export default {
  name: 'NavbarLinkList',
  data() {
    return {
      loginModal: false,
      forgotPasswordModal: false
    }
  },
  components: {
    NavbarLinkListItem,
    LoginModal,
    ForgotPasswordModal,
  },
  methods: {
    changeLogin () {
      this.loginModal = !this.loginModal
    },
    changeForgot () {
      this.forgotPasswordModal = !this.forgotPasswordModal
    },
    changeModal () {
      this.loginModal = !this.loginModal
      this.forgotPasswordModal = !this.loginModal
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn
    }
  }
}
</script>

<style>
  .navbar-nav {
    display: flex;
    flex-direction: row;
  }
</style>