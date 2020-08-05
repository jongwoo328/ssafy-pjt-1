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
                    <MessageModal v-if="messageModal" :recivername="writername" @close="reply" />
                    <slot name="body">
                      <p>{{ writername }}</p>
                      <p>{{ title }}</p>
                      <p>{{ content }}</p>
                      <p>{{ date }}</p>
                      <p>{{ senddate }}</p>
                    </slot>
                    <Button @click.native="reply" button-text="답장" />
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
  },
  data() {
    return {
        msgNo: this.msgno,
        writername: "",
        title: "",
        content: "",
        date: "",
        messageModal: false,
    }
  },
  created() {
      axios.get(`${URL.BASE_URL}/msg/detail/${this.msgNo}`)
      .then(res => {
          console.log(res)
          this.writername = res.data.writername
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