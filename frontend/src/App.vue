<template>
  <div id="app">
    <Navbar/>
    <router-view/>
  </div>
</template>

<script>
import Navbar from '@/components/nav/Navbar.vue'

export default {
  components: {
    Navbar
  }
}
</script>

<style>
  body {
    margin: 0;
  }
</style>