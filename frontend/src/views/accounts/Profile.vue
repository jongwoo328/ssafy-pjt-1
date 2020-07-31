<template>
    <div id="profile" class="container">
        <div class="container profile">
            <span class="main-text">Profile</span>
            <router-link :to="toProfileAdd"><Button type="submit" buttonText="등록"/></router-link>
            <hr>
            <ProfileFrame v-if="profileFrame" />
            <br>
            <div class="description">
                <h3>소개</h3>
            </div>
            <hr>
            <p>작성한 프로필이 없습니다.</p>
        </div>
        <div class="container profile">
            <div class="web-button">
                <router-link :to="toProfileModify" ><Button id="modifyButton" button-text="수정" /> </router-link>
            </div>
            <div class="userInfo">
                <h3 v-text="getUrlUsername + '\'s Profile'"></h3>
                <img class="userImg" :src="profileData.imgUrl" alt="profile">
            </div>
            
            <div class="mobile-box">
                <div class="description-box">
                    <h3>소개</h3>
                    <router-link :to="toProfileModify" ><Button id="modifyButton" button-text="수정" /> </router-link>
                </div>
                <hr>
                <div class="profile-box">
                    <span v-text="profileData.comment"></span>
                </div>
            </div>

            <div class="web-box">
                <div class="index">
                    <h3>소개</h3>
                </div>
                <span v-text="profileData.comment"></span>
            </div>

            <div class="service-box">
                <ServiceList :services="serviceResult" />   
            </div>
        </div>
    </div>
</template>

<script>
import ProfileFrame from '@/components/common/ProfileFrame.vue'
import Button from '@/components/common/Button.vue'
import axios from 'axios'
import ServiceList from '@/components/service/ServiceList.vue'


export default {
    name: 'Profile',
    components: {
        ProfileFrame,
        Button,
        ServiceList,
    },
    computed: {
        toProfileAdd(){
            return `/accounts/${this.getUrlUsername}/create`
        },
        toProfileModify(){
            return `/accounts/${this.getUrlUsername}/update`
        },
        getUrlUsername() {
            return this.$route.params.username
        },
        // isProfileNull() {
        //     return this.isProfile === false
        // },
        // isMyProfile() {
        //     return this.getUrlUsername === this.$store.getters.getUserData.name
        // }
    },
    created() {
        axios.get(`${URL.BASE_URL}${URL.PORT}/profile/${this.getUrlUsername}`)
        .then(res => {
            console.log(res)
            this.profileData = {
                imgUrl: `${URL.BASE_URL}${URL.PORT}` + res.data.imgurl,
                comment: res.data.comment
            }
            console.log(this.profileData.imgUrl)
            console.log(this.profileData.comment)
            if (res.data === 'fail') {
                this.isProfile = false
            }
        })
        .catch(err => {
            console.log(err)
        })
    },
    data() {
        return {
            profileFrame: true,
            isProfile: true,
            profileData: "testHaveData",
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
    #profile .description-box {
        display: flex;
        justify-content: space-between;
    }

    @media (min-width: 768px) {
        .web-box {
            display: flex;
            justify-content: space-between;
            margin-bottom: 30px;
        }
        .web-box .index {
            display: flex;
            justify-content: center;
            align-items: center;
            min-width: 150px;
            width: 30%;
            border-right: 4px solid black;
        }
        .web-box span {
            width: 60%;
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