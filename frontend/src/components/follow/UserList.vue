<template>
  <div class="user-list">
      <UserListItem v-for="user in userList" :servename="servename" :serveno="serveno" :key="user.userno" :user="user"/>
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
        servename: String,
        serveno: Number,
    }

}
</script>

<style>
    .user-list {
        display: flex;
        flex-direction: column;
        max-height: 400px;
        overflow-y:scroll;
    }
    @media (min-width: 768px) {
        .user-list {
            flex-direction: row;
            flex-wrap: wrap;
            max-height: 500px;
        }
    }
</style>