<template>
  <nav class="navbar">
    <div v-if="modal" class="back" @click="close">
    </div>
    <LoginModal v-if="loginModal" @close="changeLogin" @change="changeModal"/>
    <ForgotPasswordModal v-if="forgotPasswordModal" @close="changeForgot" @change="changeModal" />
    <button class="w3-button w3-xlarge side-open" @click="open">☰</button>
    <div class="side-list w3-sidebar w3-bar-block w3-border-right font-kor" id="mobile-sidebar">
      <div>
        <button @click="close" class="w3-bar-item w3-large side-close"><span>&times;</span></button>
        <div v-if="!isLoggedIn">
          <h3 class="text-center login-notice">로그인 필요</h3>
          <div class="buttons">
            <Button buttonText="로그인" @click.native="changeLogin"/>
            <Button buttonText="회원가입" @click.native="signup" />
          </div>
        </div>
        <a v-if="isLoggedIn" @click="routerLink" data-to='/myinfo' class="w3-bar-item sidebar-link font-kor">내 정보</a>
        <a v-if="$store.getters.isPro" @click="routerLink" data-to='/myservice' class="w3-bar-item sidebar-link font-kor">내 서비스</a>
        <a v-if="isLoggedIn" @click="routerLink" :data-to="toMyProfile" class="w3-bar-item sidebar-link font-kor">내 프로필</a>
        <a v-if="isLoggedIn" @click="routerLink" data-to='/paylist' class="w3-bar-item sidebar-link font-kor">결제내역</a>
        <a v-if="isLoggedIn" @click="routerLink" :data-to="toMyFollow" class="w3-bar-item sidebar-link font-kor">팔로우</a>
        <a v-if="isLoggedIn" @click="routerLink" data-to='/qna' class="w3-bar-item sidebar-link font-kor">Q&A</a>
      </div>
    </div>
    <a href="/" class="navbar-brand"><img src="../../assets/logo_mini.png" alt="logo"></a>
    <NavbarLinkList/>
  </nav>
</template>

<script>
import NavbarLinkList from '@/components/nav/NavbarLinkList.vue'
import Button from '@/components/common/Button.vue'
import LoginModal from '@/components/modal/LoginModal.vue'
import ForgotPasswordModal from '@/components/modal/ForgotPasswordModal.vue'

export default {
    name: 'Navbar',
    components: {
      NavbarLinkList,
      Button,
      LoginModal,
      ForgotPasswordModal
    },
    data() {
      return {
        modal: false,
        loginModal: false,
        forgotPasswordModal: false,
      }
    },
    computed: {
      toMyProfile(){
          return `/accounts/${this.$store.getters.getUserData.name}`
      },
      isLoggedIn() {
          return this.$store.getters.isLoggedIn
      },
      toMyFollow() {
          return this.toMyProfile + '/follow'
      }
    },
    watch: {
      modal() {
        const side = document.getElementById('mobile-sidebar')
        if (this.modal) {
          side.style.transform = "translateX(0)"
        } else {
          side.style.transform = "translateX(-100%)"
        }
        side.style.height = screen.height
      }
    },
    methods: {
      open() {
        this.modal = true
      },
      close() {
        this.modal = false
      },
      routerLink(e) {
        this.modal = false
        this.$router.push(e.target.dataset.to)
      },
      signup() {
        this.$router.push({name: 'SignUp'})
        this.close()
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
    },
}
</script>

<style>
  nav.navbar .back {
    z-index: 9;
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(0,0,0,0.5);
    height: 100%;
  }
  nav.navbar {
    z-index: 3;
    box-shadow: 0 1px 5px gray;
    padding: 0 !important;
    height: 70px;

    position: relative;
  }
  .navbar-brand {
    padding: 0 !important;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }
  nav.navbar img {
    width: auto;
    height: auto;
    max-height: 70px;
  }
  #mobile-sidebar {
    height:100vh;
    width: 200px;
    z-index: 10;
    position: fixed !important; 
    background-color: white;
    left: 0;
    top: 0;
    transform: translateX(-100%);
    transition: transform 0.3s linear;
  }
  #mobile-sidebar .side-close {
    display: flex;
    flex-direction: row-reverse;
    justify-content: space-between;
  }
  
  nav.navbar .side-list {
    /* margin-top: 10px; */
    display: flex;
    flex-direction: column;
  }
  nav.navbar.sidebar-link {
      display: inline-block;
      margin-left: 50px;
      margin-bottom: 20px;
      font-size: 1.5rem;
      color: black;
  }
  nav.navbar .sidebar-link:hover {
      text-decoration: none;
      color: black;
  }
  nav.navbar .sidebar-link:hover {
      display: inline-block;
      color: rgb(236,128,116);
      cursor: pointer;
  }
  nav.navbar a.w3-bar-item {
    padding: 20px 30px 20px 30px;
  }
  nav.navbar .login-notice {
    padding: 10px 0 10px 0;
  }
  nav.navbar .side-list .buttons {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding: 0 10px 0 10px;
  }
  nav.navbar .side-list .buttons button {
    margin: 10px 0 10px 0;
  }
  @media (min-width: 768px) {
    nav.navbar {
      height: 100px;
    }
    nav.navbar img {
      max-height: 100px;
    }
    nav.navbar .side-open {
      margin-left: 10px;
    }
  }
  @media (min-width: 992px) {
        nav.navbar .side-open {
      display: none;
    }
        nav.navbar .navbar-brand {
      position: static;
      transform: translateX(50%);
    }
  }
</style>