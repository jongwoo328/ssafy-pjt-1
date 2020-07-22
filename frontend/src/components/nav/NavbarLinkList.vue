<template>
  <div class="navbar-nav"> 
      <NoticeIcon/> 
      <NavbarLinkListItem v-if="!isLoggedIn" value="SignUp" @click.native="toSignUp" />
      <NavbarLinkListItem v-if="!isLoggedIn" value="Login" @click.native="changeLogin"/>
      <LoginModal v-if="loginModal" @close="changeLogin" @lost="changeModal">
      </LoginModal>
      <LostPasswordModal v-if="lostPasswordModal" @close="changeLost">
      </LostPasswordModal>
  </div>
</template>

<script>
import NavbarLinkListItem from '@/components/nav/NavbarLinkListItem.vue'
import LoginModal from '@/components/modal/LoginModal.vue'
import LostPasswordModal  from '@/components/modal/LostPasswordModal.vue'
import NoticeIcon from '@/components/common/NoticeIcon.vue'

export default {
  name: 'NavbarLinkList',
  data() {
    return {
      loginModal: false,
      lostPasswordModal: false,
    }
  },
  components: {
    NavbarLinkListItem,
    LoginModal,
    LostPasswordModal,
    NoticeIcon,
  },
  methods: {
    changeLogin () {
      this.loginModal = !this.loginModal
    },
    changeLost () {
      this.lostPasswordModal = !this.lostPasswordModal
    },
    changeModal () {
      this.loginModal = !this.loginModal
      this.lostPasswordModal = !this.loginModal
    },
    toSignUp () {
      this.$router.push({name:'SignUp'})
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