<template>
  <div id="sidebar">
      <div class="sidebar-link-list">
        <SidebarItem v-if="isLoggedIn" to='/accounts/userinfo' i="myinf" value="내 정보" />
        <SidebarItem v-if="$store.getters.isPro" to='/myservice' i="mysvc" value="내 서비스" />
        <div @mouseover="inline" @mouseout="outline" class="sidebar-link font-kor" id="myprf">
            <a v-if="isLoggedIn" :href="toMyProfile"><p>내 프로필</p></a>
            <div class="line" style="width:0px;"></div>
        </div>
        <SidebarItem v-if="isLoggedIn" to='/paylist' i="pays" value="결제내역" />
        <SidebarItem v-if="isLoggedIn" :to="toMyFollow" i="followlist" value="팔로우" />
        <SidebarItem v-if="isLoggedIn" to='/qna' i="qnalist" value="Q&A" />

        <div class="sidebar-anonymous font-kor">
            <SidebarCardList :eventList="eventList" />    
        </div>
      </div>
  </div>
</template>

<script>
import SidebarCardList from './SidebarCardList.vue'
import SidebarItem from './SidebarItem.vue'

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
            selector: '#myprf .line',
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
        SidebarCardList,
        SidebarItem
    },
    methods: {
        inline() {
            const line = document.querySelector(this.selector)
            line.style.width = "100%"
        },
        outline() {
            const line = document.querySelector(this.selector)
            line.style.width = "0px"
        }
    }

}
</script>

<style>
    #sidebar, .sidebar-link-list {
        display: none;
    }
    #myprf {
        display: inline-block;
        width: fit-content;
    }
    @media (min-width: 992px) {
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
        
    }

</style>