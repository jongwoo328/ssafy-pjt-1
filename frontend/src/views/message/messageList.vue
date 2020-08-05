<template>
  <div id="msgList">
    <div class="button">
        <button @click="changeModal">쪽지 보내기</button>
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
        }
    },
    props: {
        message: Array,
    },
    created() {
        axios.get(`${URL.BASE_URL}/msg/rec/${this.$store.getters.getUserData.userno}`)
        .then(res => {
            console.log(res.data)
            this.messagedata = res.data
        })
        .catch(err =>{
            console.log(err)
        })
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
        deleteMsg() {
            var checkboxList = document.getElementsByClassName('check-box')
            console.log(checkboxList)
            checkboxList.forEach(checkbox => {
                if (checkbox.value=='true') {
                    this.deleteno.push(checkbox.dataset.msgno)
                }
            })
            // console.log(this.deleteno)

            axios.delete(`${URL.BASE_URL}/msg/rec`, {data: this.deleteno})
            .then(res => {
                console.log(res)
                var deleteMsg = document.getElementsByClassName('msg-box')
                console.log(deleteMsg)
                deleteMsg.forEach(msg => {
                    console.log(msg.dataset.msgnum)
                    // js containment test 문법 확인중.
                    if (this.deleteno.includes(msg.dataset.msgnum)) {
                        msg.style.display = "none"
                    }
                })
            })
            .catch(err => {
                console.log(err)
            })
        }
    },
}
</script>

<style>
    #msgList .button {
        margin: 20px 20px 0 0;
        display: flex;
        justify-content: flex-end;
    }
</style>