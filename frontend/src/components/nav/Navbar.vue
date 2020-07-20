<template>
  <nav class="navbar">
      <a href="/" class="navbar-brand"><img src="../../assets/logo_mini.jpg" alt="logo"></a>
      <NavbarLinkList/>
  </nav>
</template>

<script>
import NavbarLinkList from '@/components/nav/NavbarLinkList.vue'

export default {
    name: 'Navbar',
    components: {
      NavbarLinkList,
    }
}
</script>

<style scoped>
  nav {
    box-shadow: 0 0 5px gray;
    padding-top: 0;
    padding-bottom: 0;
    height: 70px;
  }
  .navbar-brand {
    padding: 0 !important;
  }
  nav img {
    width: auto;
    height: auto;
    max-height: 70px;
  }
  @media (min-width: 768px) {
    nav {
      height: 100px;
    }
    nav img {
      max-height: 100px;
    }
  }
</style>