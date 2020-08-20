<template>
  <div id="follow" class="container font-kor">
    <GoBack/>
    <h2 class="title">팔로우</h2>
    <hr>
    <div class="myinfo">
        <div class="image">
            <img :src="profileData.imgUrl" alt="profile_image">
        </div>
        <p class="name" v-text="$store.getters.getUserData.name"></p>
        <div class="info">
            <div class="follows cursor-pointer" @click="showFollowing" style="border-bottom: none;">
                <h3>팔로잉 <span id="following-count" v-text="followingCount"></span></h3>
            </div>
            <div class="followers cursor-pointer" @click="showFollower" style="border-left: none;">
                <h3>팔로워 <span id="follower-count" v-text="followerCount"></span></h3>
            </div>
        </div>
    </div>
    <div class="follow-info">
        <UserList :userList="followUserList" />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import URL from '@/util/http-common.js'
import UserList from '@/components/follow/UserList.vue'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name: 'Follow',
    components: {
        UserList,
        GoBack
    },
    data() {
        return {
            profileData: "",
            followUserList: [],
            followingCount: 0,
            followerCount: 0,
            displayFollowing: true,
            displayFollower: false
        }
    },
    watch: {
        displayFollowing() {
            const followingElement = document.querySelector('.follows')
            const followerElement = document.querySelector('.followers')
            if (this.displayFollowing === true) {
                followingElement.style.borderBottom = "none"
                followingElement.style.backgroundColor = "white"
                followingElement.style.borderRight = "1px solid black"
                followerElement.style.borderBottom = "1px solid black"
                followerElement.style.borderLeft = "none"
            } else {
                followingElement.style.borderBottom = "1px solid black"
                followingElement.style.borderRight = "none"
                followerElement.style.borderBottom = "none"
                followerElement.style.borderLeft = "1px solid black"
                followerElement.style.backgroundColor = "white"
            }
        }
    },
    created() {
        this.$emit('sidebar')
        if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        axios.get(`${URL.BASE_URL}/follow/following/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            this.followUserList = res.data
            this.followingCount = res.data.length
        })
        .catch(err => {
            console.log(err)
        })
        axios.get(`${URL.BASE_URL}/follow/follower/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            this.followerCount = res.data.length
        })
        .catch(err => {
            console.log(err)
        })
         axios.get(`${URL.BASE_URL}/profile/username=${this.$store.getters.getUserData.name}&userno=${this.$store.getters.getUserData.userno}`)
            .then(res => {
                console.log(res)
                this.profileData = {
                    imgUrl: `${URL.IMG_URL}/` + res.data.imgurl,
                }
            })
            .catch(err => {
                console.log(err)
            })
    },
    methods: {
        showFollower() {
            this.displayFollower = true
            this.displayFollowing = false
            axios.get(`${URL.BASE_URL}/follow/follower/${this.$store.getters.getUserData.userno}`)
            .then(res => {
                const data = []
                res.data.forEach(d => {
                    d['follower'] = true
                    data.push(d)
                })
                this.followerCount = res.data.length
                this.followUserList = data
            })
            .catch(err => {
                console.log(err)
            })
        },
        showFollowing() {
            this.displayFollower = false
            this.displayFollowing = true
            axios.get(`${URL.BASE_URL}/follow/following/${this.$store.getters.getUserData.userno}`)
            .then(res => {
                this.followingCount = res.data.length
                this.followUserList = res.data
            })
            .catch(err => {
                console.log(err)
            })
        }
    }
}
</script>

<style>
    #follow {
        margin-top: 50px;
    }
    #follow .title {
        font-size: 2rem;
    }
    #follow .myinfo {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    #follow .myinfo .image{
        width: 100px;
        height: 100px;
    }
    #follow .name {
        font-size: 1.25rem;
    }
    #follow .myinfo img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
    }
    #follow .info {
        display: flex;
        flex-direction: row;
        width: 100%;
        margin: 10px 0 0 0;
    }
    #follow .info span {
        font-weight: bold;
    }
    #follow .info > div:hover {
        background-color: #d0ebff;
    }
    #follow .followers, #follow .follows {
        width: 50%;
        text-align: center;
        border: 1px solid black;
    }
    #follow .info h3 {
        font-size: 1.5rem;
    }
    #follow .follow-info {
        /* border: 1px solid black; */
        min-height: 350px;
        border-left: 1px solid black;
        border-right: 1px solid black;
        border-bottom: 1px solid black;
    }
    @media (min-width: 768px) {
        #follow .follow-info {
            min-height: 350px;
        }
    }
</style>