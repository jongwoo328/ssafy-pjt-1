<template>
  <div>
      <div class="message-list">
          <MessageListItem v-for="message in messagedata" :key="message.msgno" :message="message" />
      </div>
      <button @click="changeModal">작성</button>
      <messageModal v-if="messagemodal" @close="changeModal"/>
  </div>
</template>

<script>
import messageModal from '@/components/modal/MessageModal.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import MessageListItem from '@/views/message/MessageListItem.vue'

export default {
    name: 'MessageList',
    data() {
        return {
            messagemodal: false,
            messagedata: "",
        }
    },
    props: {
        message: Array,
    },
    created() {
        axios.get(`${URL.BASE_URL}/msg/${this.$store.getters.getUserData.userno}`)
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
    },
    methods: {
        changeModal() {
            this.messagemodal = !this.messagemodal
        }
    },
}
</script>

<style>

</style>