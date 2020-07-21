<template>
  <div id="app">
    <Navbar/>
    <Sidebar/>
    <router-view/>
  </div>
</template>

<script>
import Navbar from '@/components/nav/Navbar.vue'
import Sidebar from '@/components/common/Sidebar.vue'

export default {
  components: {
    Navbar,
    Sidebar
  }
}
</script>

<style>
  body {
    margin: 0;
  }
</style>