<template>
  <div class="navbar-nav">
      <NavbarLinkListItem value="SignUp" />
      <NavbarLinkListItem value="Login" />
  </div>
</template>

<script>
import NavbarLinkListItem from '@/components/nav/NavbarLinkListItem.vue'

export default {
  name: 'NavbarLinkList',
  components: {
    NavbarLinkListItem,
  }
}
</script>

<style scoped>
  .navbar-nav {
    display: flex;
    flex-direction: row;
  }
</style>