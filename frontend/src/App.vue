<template>
  <div id="app">
    <Navbar/>
    <Sidebar/>
    <div id="view">
      <router-view/>
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
  }
}
</script>

<style>
  body {
    margin: 0;
  }
  @media (min-width: 768px) {
    #view {
      margin-left: 250px;
    }
  }
</style>