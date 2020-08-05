<template>
    <div class="container msg-box" :data-msgNum="message.msgno">
        <MsgModal v-if="msgModal" :msgno="message.msgno" @close="msg" />
        <input type="checkbox" class="check-box" :value="value" :data-msgno="message.msgno" @click="toggle">
        <p>발신자 : {{message.writername}}</p>
        <p @click="msg">제목 : {{message.title}}</p>
        <p>읽음표시 : {{message.readcheck}}</p>
        <p>{{message.senddate}}</p>
        <hr>
    </div>
</template>

<script>
import MsgModal from '@/components/modal/MsgModal.vue'
// import checkbox from '@/components/common/checkbox.vue'

export default {
    name: 'MessageListItem',
    data() {
        return {
            msgModal: false,
            value: false,
        }
    },
    props: {
        message: Object,
    },
    components:{
        MsgModal,
    },
    methods: {
        msg() {
            this.msgModal = !this.msgModal
        },
        toggle() {
            this.value = !this.value
        }
    },
}
</script>

<style>
    .msg-box p {
        margin-bottom: 0;
    }

</style>