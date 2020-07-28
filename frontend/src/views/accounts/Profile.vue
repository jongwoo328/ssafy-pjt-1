<template>
    <div>
        <div v-if="!profileData" id="profile" class="container">
            <h3>Profile</h3>
            <hr>
            <ProfileFrame v-if="profileFrame" />
            <br>
            <div class="description">
                <h2>소개</h2>
                <Button v-if="isProfile" type="submit" buttonText="수정" />
            </div>
            <hr>
            <div v-if="!isProfile">
                <p >본인을 소개해주세요.</p>
                <router-link :to="toProfileAdd"><Button type="submit" buttonText="등록"/></router-link>
            </div>
        </div>
        <div v-else>
            <img :src="profileData.imgUrl" alt="">
        </div>
    </div>
</template>

<script>
import ProfileFrame from '@/components/common/ProfileFrame.vue'
import Button from '@/components/common/Button.vue'
import axios from 'axios'

export default {
    name: 'Profile',
    components: {
        ProfileFrame,
        Button,
    },
    computed: {
        toProfileAdd(){
            return `/accounts/${this.$store.getters.getUserData.name}/create`
        } 
    },
    created() {
        axios.get(`http://192.168.100.88:8090/profile/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            console.log(res)
            this.profileData.imgUrl = res.data.imgurl
            this.profileData.comment = res.data.comment
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
            profileData: {
                imgUrl: "",
                comment: "",
            }
        }
    },
}
</script>

<style>
    #profile {
        padding-top: 50px;
    }
    #profileAdd h3 {
      font-size: 3rem;
    }
    #profile Button {
        float: right;
    }
    #profile .description {
        display: flex;
        justify-content: space-between;
    }
    #profile .description Button {
        color: rgb(236,128,116);
        background-color: white;
    }
</style>