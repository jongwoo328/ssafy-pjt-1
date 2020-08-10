<template>
  <div id="msgList">
    <div class="router">
        <a class="link" :class="{'active': urltype}" href="/message/rec">받은 쪽지함</a>
        <a class="link" :class="{'active': !urltype}" href="/message/send">보낸 쪽지함</a>
    </div>
    <div class="button">
        <Button button-text="쪽지 보내기" @click.native="changeModal" />
        <Button button-text="전체 선택" @click.native="selectAll" />
    </div>
    <div class="message-list">
        <MessageListItem v-for="message in messagedata" :key="message.msgno" :message="message" />
    </div>
    <messageModal v-if="messagemodal" @close="changeModal"/>
    <Button @click.native="deleteMsg" button-text="삭제" />
  </div>
</template>

<script>
import messageModal from '@/components/modal/MessageModal.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import MessageListItem from '@/views/message/MessageListItem.vue'
import Button from '@/components/common/Button.vue'

export default {
    name: 'MessageList',
    data() {
        return {
            messagemodal: false,
            messagedata: "",
            deleteno: [],
            type: "",
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

        if (this.urltype) {
            axios.get(`${URL.BASE_URL}/msg/rec/${this.$store.getters.getUserData.userno}`)
            .then(res => {
                console.log(2)
                console.log(res.data)
                this.messagedata = res.data
            })
            .catch(err =>{
                console.log(err)
            })
        }
        else {
            axios.get(`${URL.BASE_URL}/msg/send/${this.$store.getters.getUserData.userno}`)
            .then(res => {
                console.log(res.data)
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
    },
    methods: {
        changeModal() {
            this.messagemodal = !this.messagemodal
        },
        selectAll() {
            var checkboxList = document.getElementsByClassName('check-box')
            console.log(checkboxList)
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

            if (this.deleteno.length == 0 || this.deleteno.length != this.messagedata.length) {
                console.log(false)
                console.log(this.deleteno.length)
                console.log(this.messagedata.length)
            }
            else console.log(true)

        },
        deleteMsg() {
            var checkboxList = document.getElementsByClassName('check-box')
            console.log(checkboxList)
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
            // console.log(this.deleteno)

            if (this.urltype) {
                axios.delete(`${URL.BASE_URL}/msg/rec`, {data: this.deleteno})
                .then(res => {
                    console.log(res)
                    var deleteMsg = document.getElementsByClassName('msg-box')
                    console.log(deleteMsg)
                    deleteMsg.forEach(msg => {
                        console.log(msg.dataset.msgnum)
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
               .then(res => {
                console.log(res)
                var deleteMsg = document.getElementsByClassName('msg-box')
                console.log(deleteMsg)
                deleteMsg.forEach(msg => {
                    console.log(msg.dataset.msgnum)
                    if (this.deleteno.includes(msg.dataset.msgnum)) {
                        msg.style.display = "none"
                    }
                })
            })
            .catch(err => {
                console.log(err)
            })
            }
        }
    },
}
</script>

<style>
    i {
        margin: 0 10px;
    }
    #msgList {
        margin: 50px 50px;
    }
    #msgList .button {
        font-size: 36px;
        margin: 20px 20px 0 0;
        display: flex;
        justify-content: flex-end;
    }
    #msgList .router .link{
        font-size: 20px;
        margin: 0 15px;
    }
    #msgList .router .link:hover{
        color: rgb(236,128,116);
        text-decoration: none;
    }
    .active {
        /* text-decoration: underline;
        color: rgb(236,128,116); */
        border-bottom: 3px solid rgb(236,128,116);
    }
</style>