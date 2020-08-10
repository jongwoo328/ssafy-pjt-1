<template>
    <div id="profile" class="container">
        <div v-if="isProfileNull" class="container profile">
            <span class="main-text">Profile</span>
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
                <h3 v-text="getUrlUsername + '\'s Profile'"></h3>
                <transition>
                    <img v-show="isLoaded" class="userImg" :src="profileData.imgUrl" alt="profile" @load="onImgLoad">            
                </transition>
                <img v-show="!isLoaded" class="userImg" src="@/assets/loading2.gif" alt="loading...">
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

            <div class="service-box">
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


export default {
    name: 'Profile',
    components: {
        ProfileFrame,
        Button,
        ServiceList,
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
            console.log('load')
            this.isLoaded = true
        }
    },
    created(){
        this.$emit('sidebar')
          axios.get(`${HTTP.BASE_URL}/service/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            console.log(res)
            this.services = res.data
            this.isProfile=true
        })
        .catch(err => {
            console.log(err)
        })
    },
    mounted() {
        setTimeout(() => {
            console.log('test')
            axios.get(`${HTTP.BASE_URL}/profile/${this.$route.params.username}`)
            .then(res => {
                console.log(res)
                this.profileData = {
                    imgUrl: `${HTTP.BASE_URL}/` + res.data.imgurl,
                    comment: res.data.comment
                }
                this.services = res.data.servList
                console.log(this.services.imgurl)
                if (res.data.pno == 0) {
                    this.isProfile=false
                }
                else {
                    this.isProfile=true
                }
                console.log(1)
                console.log(this.services[0].payCount)
                console.log(this.profileData.imgUrl)
                console.log(this.profileData.comment)
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
        border-radius: 7px;
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
        border-radius: 7px;
        }
        .web-button #modifyButton {
        display: flex;
        float: right;
        color: white;
        background-color: rgb(236,128,116);
        }
    }
</style>