<template>
  <div id="sidebar">
      <div class="sidebar-link-list">
        <router-link v-if="isLoggedIn" to='/accounts/userinfo' class="sidebar-link font-kor"><p>내 정보</p></router-link>
        <router-link v-if="$store.getters.isPro" to='/myservice' class="sidebar-link font-kor"><p>내 서비스</p></router-link>
        <router-link v-if="isLoggedIn" :to="toMyProfile" class="sidebar-link font-kor"><p>내 프로필</p></router-link>
        <router-link v-if="isLoggedIn" to='/test' class="sidebar-link font-kor"><p>결제내역</p></router-link>
        <router-link v-if="isLoggedIn" to='/test' class="sidebar-link font-kor"><p>팔로우</p></router-link>
        <router-link v-if="isLoggedIn" to='/qna' class="sidebar-link font-kor"><p>Q&A</p></router-link>
        <div v-if="!isLoggedIn" class="sidebar-anonymous font-kor">
            <SidebarCardList/>    
        </div>
      </div>
  </div>
</template>

<script>
import SidebarCardList from './SidebarCardList.vue'

export default {
    name: 'Sidebar',
    computed: {
        toMyProfile(){
            return `/accounts/${this.$store.getters.getUserData.name}`
        },
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        }
    },
    components: {
        SidebarCardList
    }

}
</script>

<style>
    #sidebar .sidebar-link-list {
        display: none;
    }
    @media (min-width: 768px) {
        #sidebar {
            z-index: 1;
            display: inline-block;
            box-shadow: 2px 5px 3px gray;
            position: absolute;
            left: 0;
            height: 150vh;
            /* width: 20%; */
            min-width: 200px;
            /* background-color: white; */
        }
        #sidebar .sidebar-link-list {
            display: flex;
            flex-direction: column;
            margin: 50px 10px 0 10px;
        }
        #sidebar .sidebar-link {
            display: inline-block;
            margin-left: 50px;
            margin-bottom: 20px;
            font-size: 1.5rem;
            color: black;
        }
        #sidebar .sidebar-link:hover {
            text-decoration: none;
            color: black;
        }
        #sidebar .sidebar-link p:hover {
            display: inline-block;
            color: rgb(236,128,116);
            border-bottom: 2px solid rgb(236,128,116);
        }

    }

</style>