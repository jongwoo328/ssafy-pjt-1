<template>
  <div class="navbar-nav"> 
      <NoticeIcon class="notice" v-if="isLoggedIn" @click.native="toMsg" /> 
      <NavbarLinkListItem v-if="!isLoggedIn" value="SignUp" id="SignUp" class="signup" @click.native="toSignUp" />
      <NavbarLinkListItem v-if="!isLoggedIn" value="Login" id="Login" @click.native="changeLogin"/>
      <NavbarLinkListItem v-if="isLoggedIn" value="Logout" id="Logout" @click.native="logout"/>
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
import NoticeIcon from '@/components/common/NoticeIcon.vue'

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
    ServiceAddModal,
    NoticeIcon
  },
  methods: {
    toMsg(){
      this.$router.push({name: "MessageList", params: {msgtype: "rec"}})
    },
    toSignUp() {
      this.$router.push({name: "SignUp"})
    },
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
    },
    logout() {
      this.$session.remove('jwstoken')
      this.$store.commit('logout')
      this.$router.go()
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn
    },
    isPro() {
      return this.$store.getters.isPro
    },
  }
}
</script>

<style>
  .navbar-nav {
    position: absolute;
    right: 0;
    display: flex;
    flex-direction: row;
    margin-right: 10px;
  }
</style>