<template>
  <nav class="navbar">
      <a href="/" class="navbar-brand"><img src="../../assets/logo_mini.png" alt="logo"></a>
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
  nav.navbar {
    z-index: 3;
    box-shadow: 0 1px 5px gray;
    padding: 0 !important;
    height: 70px;
  }
  .navbar-brand {
    padding: 0 !important;
  }
  nav.navbar img {
    width: auto;
    height: auto;
    max-height: 70px;
  }
  @media (min-width: 768px) {
    nav.navbar {
      height: 100px;
    }
    nav.navbar img {
      max-height: 100px;
    }
  }
</style>