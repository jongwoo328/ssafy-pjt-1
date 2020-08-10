<template>
  <div id="sidebar">
      <div class="sidebar-link-list">
        <router-link v-if="isLoggedIn" to='/accounts/userinfo' class="sidebar-link font-kor"><p>내 정보</p></router-link>
        <router-link v-if="$store.getters.isPro" to='/myservice' class="sidebar-link font-kor"><p>내 서비스</p></router-link>
        <a v-if="isLoggedIn" class="sidebar-link font-kor" :href="toMyProfile"><p>내 프로필</p></a>
        <router-link v-if="isLoggedIn" to='/test' class="sidebar-link font-kor"><p>결제내역</p></router-link>
        <router-link v-if="isLoggedIn" :to="toMyFollow" class="sidebar-link font-kor"><p>팔로우</p></router-link>
        <router-link v-if="isLoggedIn" to='/qna' class="sidebar-link font-kor"><p>Q&A</p></router-link>
        <div v-if="!isLoggedIn" class="sidebar-anonymous font-kor">
            <SidebarCardList :eventList="eventList" />    
        </div>
      </div>
  </div>
</template>

<script>
import SidebarCardList from './SidebarCardList.vue'

export default {
    name: 'Sidebar',
    watch: {
        getSidebar() {
            const sidebar = document.querySelector('div#sidebar')
            if (sidebar) {
                sidebar.style.height = window.screen.availHeight
            }
        }
    },
    data() {
        return {
            eventList: [
                {
                    eventno: 1,
                    etitle: 'Spring 선착순 할인',
                    econtent: '선착순 10명에게 30% 할인이 제공중입니다.'
                },
                {
                    eventno: 2,
                    etitle: 'Vue.js 리뷰이벤트',
                    econtent: '주재성의 Vue 강의를 들으신 분 중 우수리뷰어에게 쿠폰을 드립니다.'
                }
            ]
        }
    },
    computed: {
        toMyProfile(){
            return `/accounts/${this.$store.getters.getUserData.name}`
        },
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        },
        toMyFollow() {
            return this.toMyProfile + '/follow'
        },
        getSidebar() {
            return this.$store.getters.getSidebar
        },
    },
    components: {
        SidebarCardList
    }

}
</script>

<style>
    #sidebar, .sidebar-link-list {
        display: none;
    }
    @media (min-width: 768px) {
        #sidebar {
            z-index: 1;
            display: inline-block;
            box-shadow: 2px 5px 3px gray;
            position: absolute;
            left: 0;
            height: 100vh;
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