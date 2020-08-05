<template>
  <div class="user-list-item font-kor" :data-userno="user.userno">
    <div class="image">
        <img :src="getImgUrl" alt="">
    </div>
    <div class="uinfo">
        <h4 class="name" v-text="user.name"></h4>
        <div class="icons">
            <div class="follow-icon">
                <i v-if="isFollwing" class="fas fa-heart" @click="deleteFollow"></i>
                <i v-else class="far fa-heart" @click="follow"></i>
                <span class="folcount" v-text="user.folcount">10</span>
            </div>
            <i class="fas fa-envelope"></i>
            <a :href="mailTo">
                <i class="fas fa-at"></i>
            </a>
        </div>
    </div>
  </div>
</template>

<script>
import URL from '@/util/http-common.js'
import axios from 'axios'

export default {
    name: 'UserListItem',
    props: {
        user: Object
    },
    computed: {
        getImgUrl() {
            return URL.BASE_URL + '/' + this.user.addr1
        },
        isFollwing() {
            return this.user.checkfollow
        },
        mailTo() {
            return `mailto:${this.user.email}`
        }
    },
    methods: {
        deleteFollow() {
            const data = {
                userno: this.$store.getters.getUserData.userno,
                prono: this.user.userno
            }
            console.log(data)
            axios.delete(`${URL.BASE_URL}/follow`, {data: data}, URL.JSON_HEADER)
            .then(res => {
                console.log(res)
                this.user.checkfollow = false
                this.user.folcount = res.data
                const userList = document.querySelectorAll('.user-list-item')
                userList.forEach(user => {
                    console.log(user.dataset)
                    if (user.dataset.userno == this.user.userno) {
                        console.log(user)
                        user.style.display = "none"
                    }
                })
            })
            .catch(err => console.log(err))
        },
        follow() {
            const data = {
                userno: this.$store.getters.getUserData.userno,
                prono: this.user.userno
            }
            console.log(data)
            axios.post(`${URL.BASE_URL}/follow`, data, URL.JSON_HEADER)
            .then(res => {
                console.log(res)
                this.user.checkfollow = true
                this.user.folcount = res.data
            })
            .catch(err => console.log(err))
        }
    }
}
</script>

<style>
    .user-list-item {
        /* border: 1px solid black; */
        height: 60px;
        width: 100%;
        display: flex;
        flex-direction: row;

    }
    .user-list-item .uinfo {
        width: 100%
    }
    .user-list-item .image, .user-list-item .uinfo {
        height: 100%;
        /* border: 1px solid black; */
        display: flex;
        flex-direction: row;
        align-items: center;
    }
    .user-list-item .image img {
        object-fit: cover;
    }
    .user-list-item .name {
        width: 50%;
        text-align: center;
        font-size: 1.25rem;
    }
    .user-list-item .image {
        min-width: 60px;
        width: 60px;
        max-width: 60px;
        padding: 10px;
    }
    .user-list-item .image img {
        width: 100%;
        height: 100%;
    }
    .user-list-item .icons {
        width: 50% ;
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
    }
    .user-list-item .folcount {
        margin-left: 5px;
    }
    .user-list-item .fa-heart:hover {
        cursor: pointer;
    }
</style>