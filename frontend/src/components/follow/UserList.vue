<template>
  <div class="user-list">
      <UserListItem v-for="user in userList" :key="user.userno" :user="user"/>
  </div>
</template>

<script>
import UserListItem from './UserListItem.vue'

export default {
    name: 'UserList',
    components: {
        UserListItem,
    },
    props: {
        userList: Array,
    }

}
</script>

<style>
    .user-list {
        max-height: 400px;
        overflow:scroll;
    }
</style>