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

<style>
  nav {
    z-index: 3;
    box-shadow: 0 1px 5px gray;
    padding: 0 !important;
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