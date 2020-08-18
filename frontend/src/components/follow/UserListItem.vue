<template>
  <div class="user-list-item font-kor" :data-userno="user.userno" :data-follower="user.follower">
    <div class="image toProfile" @click="toProfile">
        <img :src="getImgUrl" alt="">
    </div>
    <div class="uinfo">
        <div class="name"><span class="toProfile" v-text="user.name" @click="toProfile"></span></div>
        <div class="icons">
            <div class="follow-icon">
                <i v-if="isFollwing" class="fas fa-heart" @click="deleteFollow"></i>
                <i v-else class="far fa-heart" @click="follow"></i>
                <span class="folcount" v-text="user.folcount">10</span>
            </div>
            <i class="fas fa-envelope" @click="showMsg"></i>
            <MessageModal v-if="messageModal" :Sendtype="sendno" :recivername="user.name" @close="showMsg" />
            <a :href="mailTo">
                <i class="fas fa-at"></i>
            </a>
            <Button v-if="serveno" class="refund-button" button-text="환불" @click.native="refund" />
        </div>
    </div>
  </div>
</template>

<script>
import URL from '@/util/http-common.js'
import axios from 'axios'
import MessageModal from '@/components/modal/MessageModal.vue'
import Button from '@/components/common/Button.vue'

export default {
    name: 'UserListItem',
    data() {
        return {
            messageModal: false,
            sendno: "",
        }
    },
    components: {
        MessageModal,
        Button,
    },
    props: {
        user: Object,
        Sendtype: Number,
        recivername: String,
        servename: String,
        serveno: Number,
    },
    computed: {
        getImgUrl() {
            return URL.IMG_URL + '/' + this.user.addr1
        },
        isFollwing() {
            return this.user.checkfollow
        },
        mailTo() {
            return `mailto:${this.user.email}`
        },
        calledInServiceView() {
            return String(this.$route.path).includes('/services')
        }
    },
    methods: {
        messagesend() {
            console.log(this.servename)
            let messageData={
                writername: this.$store.getters.getUserData.name,
                recivername: this.user.name,
                title: "환불 알림",
                content: '"'+this.servename+'"'+"이 환불처리되었습니다.",
            }
            axios.post(`${URL.BASE_URL}/msg`, messageData)
            .then(() => {
                alert('환불 처리 되었습니다.')  
            })
            .catch(err => {
                alert(err)
            })
        },
        refund() {
            axios.delete(`${URL.BASE_URL}/pay/pro/servno=${this.serveno}&userno=${this.user.userno}`)
            .then((res)=>{
                console.log(res)
                this.messagesend()
            }) 
            .catch(err => {
             console.log(err)
            })
        },
        showMsg() {
            this.messageModal = !this.messageModal
            this.sendno = 1
        },
        deleteFollow() {
            const data = {
                userno: this.$store.getters.getUserData.userno,
                prono: this.user.userno
            }
            axios.delete(`${URL.BASE_URL}/follow`, {data: data}, URL.JSON_HEADER)
            .then(res => {
                this.user.checkfollow = false
                this.user.folcount = res.data
                if (!this.calledInServiceView) {
                    const userList = document.querySelectorAll('.user-list-item')
                    for(let user of userList){
                        if (user.dataset.userno == this.user.userno && user.dataset.follower === undefined) {
                            user.style.display = "none"
                            break
                        }
                    }
                }
            })
            .catch(err => console.log(err))
        },
        follow() {
            const data = {
                userno: this.$store.getters.getUserData.userno,
                prono: this.user.userno
            }
            axios.post(`${URL.BASE_URL}/follow`, data, URL.JSON_HEADER)
            .then(res => {
                this.user.checkfollow = true
                this.user.folcount = res.data
            })
            .catch(err => console.log(err))
        },
        toProfile() {
            this.$router.push(`/accounts/${this.user.name}`)
        }
    }
}
</script>

<style>
    .refund-button {
        color: rgb(236,128,116) !important;
        background-color: white !important;
    }
    .user-list-item {
        /* border: 1px solid black; */
        height: 60px;
        width: 100%;
        display: flex;
        flex-direction: row;
        transition: font-size 0.2s ease;
    }
    .user-list-item:hover {
        box-shadow: 0 1px 5px gray;
    }
    .user-list-item .fa-heart {
        color: crimson;
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
        object-fit:cover;
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
        border-radius: 50%;
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
    .user-list-item .fa-envelope:hover {
        cursor: pointer;
    }
    .user-list-item div.toProfile:hover {
        cursor: pointer;
        border: 1px solid rgb(236, 128, 116);
    }
    .user-list-item span.toProfile:hover {
        cursor: pointer;
        border-bottom: 2px solid rgb(236,128,116);
    }
    @media (min-width: 768px) {
        .user-list-item {
            width: 50%;
        }
    }
</style>