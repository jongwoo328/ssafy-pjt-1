<template>
    <div class="containe">
        <div class="msg-box" :data-msgnum="message.msgno">
            <MsgModal v-if="msgModal" :msgno="message.msgno" @close="msg" />
            <input type="checkbox" class="check-box" v-model="value" :value="value" :data-msgno="message.msgno" @click="toggle">
            <span @click="msg" class="message-title" :class="{'read': message.readcheck}">{{ message.title }}</span>
            <br>
            <p v-if="this.$route.params.msgtype=='send'">{{ message.recivername }}</p>
            <p v-else>{{ message.writername }}</p>
            <p v-if="message.readcheck" class="messsage-date"><i class="far fa-envelope-open"></i>{{ message.senddate }}</p>
            <p v-if="!message.readcheck" class="message-date"><i class="far fa-envelope"></i>{{ message.senddate }}</p>
            <hr>
        </div>
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
    created() {
        this.$emit('sidebar')
    },
    props: {
        message: Object,
        checked: Boolean,
    },
    components:{
        MsgModal,
    },
    methods: {
        msg() {
            this.msgModal = !this.msgModal
            if (this.$route.params.msgtype == 'rec') {
                this.message.readcheck = true
            }
        },
        toggle() {
            this.value = !this.value
        }
    },
    watch: {
        checked: function() {
            this.value = this.checked
        }
    },
}
</script>

<style>
    .msg-box {
        margin: 0;
    }
    .msg-box p {
        margin-bottom: 0;
        text-align: end;
    }
    .msg-box .message-title {
        margin-left: 10px;
        font-size: 1.25rem;
    }
    .msg-box .message-title:hover {
        color: rgb(236,128,116);
        cursor: pointer;
    }
    .msg-box .read {
        color: darkgray;
    }
</style>