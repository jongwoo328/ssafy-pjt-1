import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

// const session = this.$session
export default new Vuex.Store({
  plugins: [createPersistedState({
    storage: window.sessionStorage,
  })],
  state: {
    // isLoggedIn: false,
    userData: null,
    sidebar: false,
  },
  mutations: {
    login(state, userData) {
      state.isLoggedIn = true
      state.userData = userData
    },
    logout(state) {
      console.log('test')
      state.isLoggedIn = false
      state.userData = null
      // this.$router.go('/')
    },
    switchSidebar(state) {
      state.sidebar = !state.sidebar
    },
    setMsgCount(state, msgcount) {
      state.userData.msgcount = msgcount
    },
    setUserInfo(state, userinfo) {
      state.userData.tel = userinfo.tel
      state.userData.addr1 = userinfo.addr1
      state.userData.addr2 = userinfo.addr2
      state.userData.addr3 = userinfo.addr3
      state.userData.addr4 = userinfo.addr4
      state.userData.addr5 = userinfo.addr5
      state.userData.addr6 = userinfo.addr6
      
    }
  },
  actions: {
    setSidebar() {
      setTimeout(() => {
        const sidebar = document.querySelector('div#sidebar')
        if (sidebar) {
          const h = document.querySelector('div#app').scrollHeight + 200
          sidebar.setAttribute('style', `height: ${h}px`)
        }
      }, 200);
    }
  },
  modules: {
  },
  getters: {
    isLoggedIn(state) {
      if (state.userData === null) {
        return false
      }
      else {
        return true
      }
    },
    getUserData(state) {
      return state.userData
    },
    isPro(state) {
      if (state.userData) {
        return state.userData.ispro
      } else {
        return false
      }
    },
    isAdmin(state) {
      return state.userData.checkadmin
    },
    sidebarState(state) {
      return state.sidebar
    }
  }
})
