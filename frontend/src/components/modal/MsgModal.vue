<template>
  <transition name="modal">
    <div id="msgModal">
        <div class="modal-mask">
          <div class="modal-wrapper">
              <div class="modal-container">
                <div class="modal-header">
                  <h3>Message</h3>
                  <button type="button" class="close" data-dismiss="modal" @click="modalclose">×</button>
                </div>
                <div class="modal-body">
                    <MessageModal v-if="messageModal" :recivername="writer" :Title="title" :Content="content" :Sendtype="sendno" @close="reply" />
                    <slot name="body">
                      <p v-if="this.$route.params.msgtype=='rec'">{{ writer }}</p>
                      <p v-else>{{ reciver }}</p>
                      <p>{{ title }}</p>
                      <p>{{ content }}</p>
                      <p>{{ date }}</p>
                      <p>{{ senddate }}</p>
                    </slot>
                    <Button v-if="this.$route.params.msgtype=='rec'" @click.native="reply" button-text="답장" />
                    <div class="Button-box">
                      <Button v-if="this.$route.params.msgtype=='send'" @click.native="relay" button-text="전달" />
                      <Button v-if="this.$route.params.msgtype=='send'" @click.native="resend" button-text="다시보내기" />
                    </div>                
                </div>
                <hr>
              </div>
          </div>
        </div>
        </div>
    </transition>
</template>


<script>
import axios from 'axios'
import URL from "@/util/http-common.js"
import Button from '@/components/common/Button.vue'
import MessageModal from '@/components/modal/MessageModal.vue'

export default {
  name: 'msgModal',
  components: {
      Button,
      MessageModal,
  },
  props: {
      msgno: Number,
      recivername: String,
      Title: String,
      Content: String,
      Sendtype: Number,
  },
  data() {
    return {
        msgNo: this.msgno,
        writer: "",
        reciver: "",
        title: "",
        content: "",
        date: "",
        messageModal: false,
        sendno: "",
    }
  },
  created() {
      axios.get(`${URL.BASE_URL}/msg/detail/msgNo=${this.msgNo}&msgtype=${this.$route.params.msgtype}`)
      .then(res => {
          console.log(res)
          this.reciver = res.data.recivername
          this.writer = res.data.writername
          this.title = res.data.title
          this.content = res.data.content
          this.date = res.data.senddate
      })
      .catch(err => {
          console.log(err)
      })
  },
  methods: {
    reply () {
     this.messageModal = !this.messageModal
     this.sendno = 1
    },
    relay () {
      this.messageModal = !this.messageModal
      this.sendno = 2
    },
    resend () {
      this.messageModal = !this.messageModal
      this.sendno = 3
    },
    modalclose () {
      this.$emit('close')
    },
  },
}
</script>

<style>
    /* model */
  #msgModal hr {
    margin-top: 0;
  }
  #msgModal .form button {
    width: 100%;
  }
  #msgModal .modalfooter {
    display: block;
    text-align: right;
    margin: 0 20px;
  }
  #msgModal .input-box {
   margin-bottom: 10px;
  }
  #msgModal .input-box input {
    width: 100%;
    height: 40px;
    border: 0.8px;
    padding-left: 10px;
    border-style: none none solid none;
  }
  #msgModal .input-label {
    font-size: 16px;
    margin: 0 0 10px 0;
    padding: 8px 0 0 0;
    text-align: right;
    font-weight: bolder
  }

  #msgModal .login-type {
    display: flex;
    justify-content: space-around;
  }

  #msgModal .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  #msgModal .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  #msgModal .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 10px;
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }
  #msgModal .modal-header {
    padding-top: 0;
    padding-bottom: 0;
  }
  #msgModal .modal-header h3 {
    margin-top: 0;
    color: rgb(236,128,116);
  }

  #msgModal .modal-body {
    margin: 0 0;
  }
  #msgModal .modal-body button {
    margin: 10px 0 10px 0;
    /* padding: 10px 0; */
  }
  #msgModal .modal-default-button {
    float: right;
  }
  #msgModal .Button-box {
    display: flex;
    justify-content: flex-end;
  }
  #msgModal .Button-box Button{
    margin-left: 20px;
  }

  /*
  * The following styles are auto-applied to elements with
  * transition="modal" when their visibility is toggled
  * by Vue.js.
  *
  * You can easily play with the modal transition by editing
  * these styles.
  */

  #msgModal .modal-enter {
    opacity: 0;
  }

  #msgModal .modal-leave-active {
    opacity: 0;
  }

  #msgModal .modal-enter .modal-container,
  #msgModal .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }

  #msgModal .error-msg {
  width: 100%;
  float: left;
  color: #EE4B55;
  font-size: 14px;
  }

  #msgModal textarea {
      width: 100%;
      min-height: 100px;
  }
  @media (min-width: 768px) {
    
    #msgModal .modal-container {
      width: 30%;
    }
  }
</style>