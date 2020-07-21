<template>
  <div class="navbar-nav">
      <NavbarLinkListItem value="SignUp" />
      <NavbarLinkListItem value="Login" @click.native="changeLogin"/>
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

export default {
  name: 'NavbarLinkList',
  data() {
    return {
      loginModal: false,
      lostPasswordModal: false
    }
  },
  components: {
    NavbarLinkListItem,
    LoginModal,
    LostPasswordModal,
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
    }
  },
}
</script>

<style scoped>
  .navbar-nav {
    display: flex;
    flex-direction: row;
  }
</style>