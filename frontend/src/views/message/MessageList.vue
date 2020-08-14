<template>
  <div id="msgList" class="container">
    <GoBack/>
    <div class="router">
        <a class="link" :class="{'active': urltype}" href="/message/rec">받은 쪽지함</a>
        <a class="link" :class="{'active': !urltype}" href="/message/send">보낸 쪽지함</a>
    </div>
    <div class="buttons">
        <input type="checkbox" v-model="checked" @click="selectAll">
        <!-- <Button button-text="전체 선택" @click.native="selectAll" /> -->
        <Button button-text="쪽지 보내기" @click.native="changeModal" />
    </div>
    <br>
    <div class="message-list">
        <MessageListItem v-for="message in messagedata" :key="message.msgno" :message="message" :checked="checked" />
    </div>
    <messageModal v-if="messagemodal" @close="changeModal"/>
    <div class="delete font-kor">
        <div v-if="deleteCheck" class="delete-check">
            <p>정말 삭제하시겠습니까?</p>
            <div class="buttons">
                <Button buttonText="아니오" buttonColor="#343a40" @click.native="deleteNo"/>
                <Button buttonText="예" buttonColor="#e03131" @click.native="deleteYes"/>
            </div>
        </div>
    </div>
    <Button @click.native="deleteCheck = !deleteCheck" button-text="삭제" />
  </div>
</template>

<script>
import messageModal from '@/components/modal/MessageModal.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import MessageListItem from '@/components/common/MessageListItem.vue'
import Button from '@/components/common/Button.vue'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name: 'MessageList',
    data() {
        return {
            deleteCheck: false,
            messagemodal: false,
            messagedata: "",
            deleteno: [],
            type: "",
            checked: false,
        }
    },
    props: {
        message: Array,
    },
    created() {
        if (this.$route.params.msgtype === "rec") {
            this.urltype = true
        }
        else {
            this.urltype = false
        } 
        if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        if (this.$route.params.msgtype === "rec") this.urltype = true
        else this.urltype = false

        if (this.urltype) {
            axios.get(`${URL.BASE_URL}/msg/rec/${this.$store.getters.getUserData.userno}`)
            .then(res => {
                this.messagedata = res.data
            })
            .catch(err =>{
                console.log(err)
            })
        }
        else {
            axios.get(`${URL.BASE_URL}/msg/send/${this.$store.getters.getUserData.userno}`)
            .then(res => {
                this.messagedata = res.data
            })
            .catch(err =>{
                console.log(err)
            })
        }
    },
    components: {
        messageModal,
        MessageListItem,
        Button,
        GoBack
    },
    methods: {
        deleteNo() {
        this.deleteCheck = false
        },
        changeModal() {
            this.messagemodal = !this.messagemodal
        },
        selectAll() {
            this.checked = !this.checked
            var checkboxList = document.getElementsByClassName('check-box')
            checkboxList.forEach(checkbox => {
                if (checkbox.value=='true') {
                    if (!this.deleteno.includes(checkbox.dataset.msgno))
                        this.deleteno.push(checkbox.dataset.msgno)
                }
                else {
                    if (this.deleteno.includes(checkbox.dataset.msgno)) {
                        const idx = this.deleteno.indexOf(checkbox.dataset.msgno)
                        this.deleteno.splice(idx,1)
                    }
                }
            })

            if (!this.checked) {
                checkboxList.forEach(checkbox => {
                    if (this.deleteno.includes(checkbox.dataset.msgno)) {
                        const idx = this.deleteno.indexOf(checkbox.dataset.msgno)
                        this.deleteno.splice(idx,1)
                    }
                })
            }
            else {
                checkboxList.forEach(checkbox => {
                    if (!this.deleteno.includes(checkbox.dataset.msgno)) {
                        this.deleteno.push(checkbox.dataset.msgno)
                    }
                })
            }
        },
        deleteYes() {
            var checkboxList = document.getElementsByClassName('check-box')
            checkboxList.forEach(checkbox => {
                if (checkbox.value=='true') {
                    if (!this.deleteno.includes(checkbox.dataset.msgno))
                        this.deleteno.push(checkbox.dataset.msgno)
                }
                else {
                    if (this.deleteno.includes(checkbox.dataset.msgno)) {
                        const idx = this.deleteno.indexOf(checkbox.dataset.msgno)
                        this.deleteno.splice(idx,1)
                    }
                }
            })

            if (this.urltype) {
                axios.delete(`${URL.BASE_URL}/msg/rec`, {data: this.deleteno})
                .then(() => {
                    var deleteMsg = document.getElementsByClassName('msg-box')
                    deleteMsg.forEach(msg => {
                        if (this.deleteno.includes(msg.dataset.msgnum)) {
                            msg.style.display = "none"
                        }
                    })
                })
                .catch(err => {
                    console.log(err)
                })
            }
            else {
                axios.delete(`${URL.BASE_URL}/msg/send`, {data: this.deleteno})
               .then(() => {
                    var deleteMsg = document.getElementsByClassName('msg-box')
                    deleteMsg.forEach(msg => {
                        if (this.deleteno.includes(msg.dataset.msgnum)) {
                            msg.style.display = "none"
                        }
                    })
                })
                .catch(err => {
                    console.log(err)
                })
            }
            this.deleteCheck = false
        }
    },
}
</script>

<style>
    i {
        margin: 0 10px;
    }
    #msgList .delete {
        width: 100%;
        position: relative;
    }
    #msgList {
        margin-top: 50px;
    }
    #msgList .buttons {
        margin: 20px 0 0 0;
        display: flex;
        justify-content: space-between;
    }
    #msgList .buttons input {
        margin-top: auto;
    }
    #msgList Button {
    font-size: 1rem;
    margin-left: 5px;
    margin-right: 5px;
  }
    #msgList .router .link{
        font-size: 20px;
        margin: 0 15px;
    }
    #msgList .router .link:hover{
        color: rgb(236,128,116);
        cursor: pointer;
        text-decoration: none;
    }
    #msgList .active {
        /* text-decoration: underline;
        color: rgb(236,128,116); */
        border-bottom: 3px solid rgb(236,128,116);
    }
    #msgList .delete-check {
    width: 200px;
    height: 100px;
    position: absolute;
    /* border: 1px solid black; */
    box-shadow: 0 1px 5px gray;
    border-radius: 7px;
    left: 20px;
    top: 50px;
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    z-index: 5;
    }
    #msgList .delete-check p {
        margin: 0;
    }
    @media (min-width: 768px) {
    #msgList .delete-check {
      width: 300px;
    }
    #msgList .buttons Button{
        font-size: 1.25rem;
    }
  }
</style>