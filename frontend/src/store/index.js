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
    isLoggedIn: false,
    userData: null,
    sidebar: false,
  },
  mutations: {
    login(state, userData) {
      state.isLoggedIn = true
      state.userData = userData
    },
    logout(state) {
      state.isLoggedIn = false
      state.userData = null
    },
    switchSidebar(state) {
      state.sidebar = !state.sidebar
    }
  },
  actions: {
  },
  modules: {
  },
  getters: {
    isLoggedIn(state) {
      return state.isLoggedIn
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
