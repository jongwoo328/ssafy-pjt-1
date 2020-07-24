<template>
  <div class="navbar-nav"> 
      <NoticeIcon/> 
       <NavbarLinkListItem v-if="!isLoggedIn" value="서비스" @click.native="toserviceUp" />
      <NavbarLinkListItem v-if="!isLoggedIn" value="SignUp" @click.native="toSignUp" />
      <NavbarLinkListItem v-if="!isLoggedIn" value="Login" @click.native="changeLogin"/>
      <LoginModal v-if="loginModal" @close="changeLogin" @change="changeModal">
      </LoginModal>
      <ForgotPasswordModal v-if="forgotPasswordModal" @close="changeForgot" @change="changeModal">
      </ForgotPasswordModal>
      <ServiceAddModal v-if="serviceAddModal" @close="toserviceUp"/>
  </div>
</template>

<script>
import NavbarLinkListItem from '@/components/nav/NavbarLinkListItem.vue'
import LoginModal from '@/components/modal/LoginModal.vue'
import ForgotPasswordModal  from '@/components/modal/ForgotPasswordModal.vue'
import ServiceAddModal from '@/components/modal/ServiceAddModal.vue'

export default {
  name: 'NavbarLinkList',
  data() {
    return {
      loginModal: false,
      forgotPasswordModal: false,
      serviceAddModal: false
    }
  },
  components: {
    NavbarLinkListItem,
    LoginModal,
    ForgotPasswordModal,
    ServiceAddModal
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
    },
    toserviceUp(){
      this.serviceAddModal = !this.serviceAddModal;
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