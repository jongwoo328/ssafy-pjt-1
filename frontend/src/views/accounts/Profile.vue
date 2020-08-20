<template>
    <div id="profile" class="container">
        <GoBack/>
        <MessageModal v-if="messageModal" :recivername="getUrlUsername" :Sendtype="sendno" @close="message" />
        <div v-if="isProfileNull" class="container profile">
            <span class="main-text">프로필</span>
            <router-link v-if="isMyProfile" :to="toProfileAdd"><Button type="submit" buttonText="등록"/></router-link>
            <hr>
            <ProfileFrame v-if="profileFrame" />
            <br>
            <div class="description">
                <h3>소개</h3>
            </div>
            <hr>
            <p>작성한 프로필이 없습니다.</p>
        </div>
        <div v-else class="container profile">
            <div class="web-button">
                <router-link v-if="isMyProfile" :to="toProfileModify" ><Button id="modifyButton" buttonText="수정" buttonColor="white" textColor="rgb(236,128,116)"/> </router-link>
            </div>
            <div class="userInfo">
                <h3 v-text="getUrlUsername + '의 프로필'"></h3>
                <transition>
                    <img v-show="isLoaded" class="userImg" :src="profileData.imgUrl" alt="profile" @load="onImgLoad">            
                </transition>
                <img v-show="!isLoaded" class="userImg" src="@/assets/loading2.gif" alt="loading...">
                <div class="profile-follow">
                    <div class="following">
                        <p>팔로잉</p>
                        <span>{{followingCount}}</span>
                        <Button @click.native="follow" v-if="!isMyProfile && !checkfollow" button-text="팔로우" />
                        <Button @click.native="unfollow" v-if="!isMyProfile && checkfollow" button-text="팔로우 취소" button-color="gray"/>
                    </div>
                    <div class="follower">
                        <p>팔로워</p>
                        <span>{{followerCount}}</span>
                        <Button v-if="!isMyProfile" button-text="메세지" @click.native="message" />
                    </div>
                </div>   
            </div>
            
            <div class="mobile-box">
                <div class="description-box">
                    <h3>소개</h3>
                    <router-link v-if="isMyProfile" :to="toProfileModify" ><Button id="modifyButton" buttonText="수정" buttonColor="white" textColor="rgb(236,128,116)"/> </router-link>
                </div>
                <hr>
                <div class="profile-box">
                    <span class="comment" v-html="profileData.comment"></span>
                </div>
            </div>

            <div class="web-box container">
                <h3>소개</h3>
                <hr>
                <div class="comment" v-html="profileData.comment"></div>
            </div>

            <div v-if="profile.ispro" class="service-box">
                <h3>제공하는 서비스</h3>
                <hr>
                <ServiceList :services="services" />   
            </div>
        </div>
    </div>
</template>

<script>
import ProfileFrame from '@/components/common/ProfileFrame.vue'
import Button from '@/components/common/Button.vue'
import axios from 'axios'
import ServiceList from '@/components/service/ServiceList.vue'
import HTTP from "@/util/http-common.js"
import MessageModal from '@/components/modal/MessageModal.vue'
import GoBack from '@/components/common/GoBack.vue'


export default {
    name: 'Profile',
    components: {
        ProfileFrame,
        Button,
        ServiceList,
        MessageModal,
        GoBack
    },
    computed: {
        toProfileAdd(){
            return `/accounts/${this.$store.getters.getUserData.name}/create`
        },
        toProfileModify(){
            return `/accounts/${this.$store.getters.getUserData.name}/update`
        },
        getUrlUsername() {
            return this.$route.params.username
        },
        isProfileNull() {
            return this.isProfile === false
        },
        isMyProfile() {
            return this.getUrlUsername === this.$store.getters.getUserData.name
        }
    },
    methods: {
        onImgLoad () {
            this.isLoaded = true
        },
        message () {
            this.messageModal = !this.messageModal
            this.sendno = 1
        },
        follow() {
            const data = {
                userno: this.$store.getters.getUserData.userno,
                prono: this.profile.userno
            }
            axios.post(`${HTTP.BASE_URL}/follow`, data, URL.JSON_HEADER)
            .then(res => {
                this.checkfollow = true
                this.followerCount = res.data
            })
            .catch(err => console.log(err))
        },
        unfollow() {
            const data = {
                userno: this.$store.getters.getUserData.userno,
                prono: this.profile.userno
            }
            axios.delete(`${HTTP.BASE_URL}/follow`, {data: data}, URL.JSON_HEADER)
            .then(res => {
                this.checkfollow = false
                this.followerCount = res.data
            })
            .catch(err => console.log(err))
        }
    },
    watch: {
        userno : function() {
            axios.get(`${HTTP.BASE_URL}/follow/following/${this.userno}`)
            .then(res => {
                this.followUserList = res.data
                this.followingCount = res.data.length
            })
            .catch(err => {
                console.log(err)
            })
            axios.get(`${HTTP.BASE_URL}/follow/follower/${this.userno}`)
            .then(res => {
                this.followerCount = res.data.length
            })
            .catch(err => {
                console.log(err)
            })
        }
    },
    created(){
        if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        setTimeout(() => {
            this.$emit('sidebar')
            axios.get(`${HTTP.BASE_URL}/profile/username=${this.$route.params.username}&userno=${this.$store.getters.getUserData.userno}`)
            .then(res => {
                this.profile = res.data
                this.checkfollow = res.data.checkfollow
                this.userno = res.data.userno
                if (res.data==='fail') {
                    this.$router.push({
                        name: 'Error',
                        query: {
                            status: 404
                        }
                    })
                }
                this.profileData = {
                    imgUrl: `${HTTP.IMG_URL}/` + res.data.imgurl,
                    comment: res.data.comment
                }
                this.services = res.data.servList
                if (res.data.pno == 0) {
                    this.isProfile=false
                }
                else {
                    this.isProfile=true
                }
                if (res.data === 'fail') {
                    this.isProfile = false
                }
            })
            .catch(err => {
                console.log(err)
            })
        }, 500);
    },
    data() {
        return {
            profile: null,
            checkfollow: false,
            writer: "",
            sendno: "",
            messageModal: false,
            userno: "",
            followUserList: "",
            followingCount: "",
            followerCount: "",
            profileFrame: true,
            isProfile: true,
            profileData: "testHaveData",
            isLoaded: false,
            services:[],
            serviceResult: [
                // 예시 표시용
                {
                s_no: 1,
                s_name: '서비스 이름',
                description: '서비스 설명',
                seraddr: '주소',
                price: '100000000000',
                u_no: '프로',
                c_no: 123123
                }
            ]
        }
    },
}
</script>

<style>
    .web-box {
        display: none;
    }
    .web-button Button {
        display: none;
    }
    #profile .userImg {
        display: flex;
        justify-content: center;
        height: 150px;
        width: 150px;
        object-fit: cover;
        margin-top: 20px;
        border-radius: 50%;
    }
    #profile {
        padding: 0 30px;
    }
    #profile hr {
        
        margin: 20px 0;
    }
    #profile .profile {
        padding-top: 50px;
    }
    #profile .main-text {
      font-size: 2rem;
    }
    #profile Button {
        float: right;
    }
    #profile .description {
        display: flex;
        justify-content: space-between;
    }
    #profile .profile-follow {
        margin: 15px 0;
        display: flex;
        
        justify-content: center;
        font-size: 1.25rem;
    }
    #profile .profile-follow p {
        margin-bottom: 0;
    }
    #profile .profile-follow span {
        display: block;
        margin-bottom: 10px;
    }
    #profile .following {
        margin-right: 15px;
        text-align: center;
    }
    #profile .follower {
        margin-left: 15px;
        text-align: center;
    }
    #modifyButton {
        color: rgb(236,128,116);
        background-color: white;
    }
    #profile .userInfo {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 30px;
    }
    #profile .userImg {
        margin-bottom: 10px;
        
    }
    #profile Button {
        height: 40px;
        font-size: 17px;
    }
    #profile .web-box{
        margin-top: 50px;
        min-height: 100px;
    }
    #profile .profile-box {
        margin: 20px 0 35px 0;
    }
    #profile .comment p{
        margin-bottom: 0 !important;
    }
    #profile .description-box {
        display: flex;
        justify-content: space-between;
    }

    @media (min-width: 768px) {
        .web-box {
            display: block;
            max-width: 700px;
        }
        .web-box div.comment {
            min-height: 200px;
        }
        .mobile-box {
            display: none;
        }
        #profile .userImg {
        display: flex;
        justify-content: center;
        object-fit: cover;
        margin-top: 20px;
        width: 300px;
        height: 300px;
        border-radius: 50%;
        }
        .web-button #modifyButton {
        display: flex;
        float: right;
        color: white;
        background-color: rgb(236,128,116);
        }
    }
</style>