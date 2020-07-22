import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// const session = this.$session
export default new Vuex.Store({
  state: {
    isLoggedIn: false,
  },
  mutations: {
    login(state) {
      state.isLoggedIn = true
    },
    logout(state) {
      state.isLoggedIn = false
    }
  },
  actions: {
  },
  modules: {
  },
  getters: {
    isLoggedIn(state) {
      return state.isLoggedIn
    }
  }
})