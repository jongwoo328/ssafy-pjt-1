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
        display: flex;
        flex-direction: column;
        max-height: 400px;
        overflow:scroll;
    }
    @media (min-width: 768px) {
        .user-list {
            flex-direction: row;
            flex-wrap: wrap;
        }
    }
</style>