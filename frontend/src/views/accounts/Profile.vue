<template>
    <div id="profile" class="container">
        <div v-if="isProfileNull" class="container profile">
            <span class="main-text">Profile</span>
            <hr>
            <ProfileFrame v-if="profileFrame" />
            <br>
            <div class="description">
                <h2>소개</h2>
            </div>
            <hr>
            <div v-if="!isProfile">
                <p>작성한 프로필이 없습니다.</p>
                <!-- <router-link v-if="isMyProfile" :to="toProfileAdd"><Button type="submit" buttonText="등록"/></router-link> -->
            </div>
        </div>
        <div v-else class="container profile">
            <div class="userInfo">
                <h3 v-text="getUrlUsername + '\'s Profile'"></h3>
                <!-- <img class="userImg" :src="profileData.imgUrl" alt="profile"> -->
                <img class="userImg" src="data:image/jpeg;base64,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" alt="profile">
            </div>
            <div class="mobile-box">
                <div class="description-box">
                    <h3>소개</h3>
                    <Button id="modifyButton" button-text="수정" @click.native="modifyProfile"/> 
                </div>
                <hr>
                <div class="profile-box">
                    <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi quia quos asperiores. Fugit velit, non similique, provident ipsum ut eligendi labore, amet distinctio quibusdam unde corporis alias molestias ullam tempore.
                    </span>
                </div>
            </div>

            <div class="web-box">
                <div class="index">
                    <h3>소개</h3>
                </div>
                <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi quia quos asperiores. Fugit velit, non similique, provident ipsum ut eligendi labore, amet distinctio quibusdam unde corporis alias molestias ullam tempore.
                </span>
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
            return `/accounts/${this.$store.getters.getUserData.name}/create`
        },
        getUrlUsername() {
            return this.$route.params.username
        },
        isProfileNull() {
            return this.profileData === null
        },
        isMyProfile() {
            return this.getUrlUsername === this.$store.getters.getUserData.name
        }
    },
    created() {
        axios.get(`http://192.168.100.88:8090/profile/${this.getUrlUsername}`)
        .then(res => {
            console.log(res)
            this.profileData = {
                imageUrl: res.data.imgUrl,
                comment: res.data.comment
            }
            console.log(this.profileData.imgUrl)
        })
        .catch(err => {
            console.log(err)
        })
    },
    data() {
        return {
            profileFrame: true,
            isProfile: false,
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
    #profile {
        padding: 0 30px;
    }
    #profile hr {
        margin: 0 0;
    }
    #profile .profile {
        padding-top: 50px;
    }
    #profile .main-text {
      font-size: 3rem;
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
    }
</style>