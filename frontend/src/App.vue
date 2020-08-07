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
          console.log(sidebar)
          console.log('test')
          console.log(window.screen.availHeight)
          console.log(document.querySelector('div#view').scrollHeight)
          sidebar.setAttribute('style', `height: ${document.querySelector('div#view').scrollHeight}px`)
          // sidebar.style.height = window.screen.availHeight + 100
          // sidebar.setAttribute('style', `height: ${window.screen.availHeight + 200}px;`)
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
      margin-left: 200px;
    }
  }
</style>