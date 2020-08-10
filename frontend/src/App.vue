<template>
  <div id="app">
    <Navbar/>
    <Sidebar/>
    <div id="view">
      <router-view @sidebar="sidebar" />
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/nav/Navbar.vue'
import Sidebar from '@/components/common/Sidebar.vue'

export default {
  components: {
    Navbar,
    Sidebar
  },
  methods: {
    sidebar() {
    const sidebar = document.querySelector('div#sidebar')
      if (sidebar) {
        sidebar.setAttribute('style', `height: ${document.querySelector('div#view').scrollHeight}px`)
      }
    }
  }
}
</script>

<style>
  body {
    margin: 0;
  }
  @media (min-width: 768px) {
    #view {
      min-height: 100vh;
      margin-left: 200px;
    }
  }
</style>