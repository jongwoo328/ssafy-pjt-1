import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggedIn: false,
  },
  mutations: {
    login(state, token) {
      state.isLoggedIn = true
      this.$session.set('jwstoken', token)
    },
    logout(state) {
      state.isLoggedIn = false
      this.$session.remove('jwstoken')
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
