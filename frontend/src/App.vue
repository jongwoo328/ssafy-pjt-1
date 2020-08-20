<template>
  <div id="app">
    <Navbar/>
    <Sidebar/>
    <div id="view">
      <router-view @sidebar="sidebar"/>
    </div>
    <ScrollUpButton/>
  </div>
</template>

<script>
import Navbar from '@/components/nav/Navbar.vue'
import Sidebar from '@/components/common/Sidebar.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import ScrollUpButton from '@/components/common/ScrollUpButton.vue'

export default {
  components: {
    Navbar,
    Sidebar,
    ScrollUpButton
  },
  methods: {
    sidebar() {
      this.$store.dispatch('setSidebar')

      axios.get(`${URL.BASE_URL}/msg/count/${this.$store.getters.getUserData.userno}`)
      .then(res => {
        this.$store.commit("setMsgCount", res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
  }
}
</script>

<style>
  body {
    margin: 0;
  }
  #view {
    margin-top: 50px;
  }
  @media (min-width: 992px) {
    #view {
      min-height: 100vh;
      margin-left: 200px;
      padding-bottom: 150px;
    }
  }
</style>