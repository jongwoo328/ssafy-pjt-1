<template>
  <transition name="modal">
    <div id="messageModal" @keyup.down="selectValue('down')" @keyup.up="selectValue('up')">
        <div class="modal-mask">
          <div class="modal-wrapper">
              <div class="modal-container">
                <div class="modal-header">
                  <h3>Message</h3>
                  <button type="button" class="close" data-dismiss="modal" @click="modalclose">×</button>
                </div>
                <div class="modal-body">
                    <slot name="body">
                      <div class="form">
                        <div class="input-box font-notojp">
                          <label class="input-label" for="inputname">TO</label>
                          <input class="s" autocomplete="off" type="text" id="inputname" placeholder="받는사람을 입력하세요." v-model="toUsername" @input="nameCheck">
                          <ul class="r" v-if="isActive">
                              <li tabindex="-1" v-for="(el,index) in filters" :key="index" @click="changeValue(el)" @keyup.enter="selectValue('enter', el)"><span>{{ el }}</span></li>
                          </ul>
                        </div>
                        <div class="input-box font-notojp">
                          <label class="input-label" for="inputtitle">TITLE</label>
                          <input type="text" id="inputtitle" placeholder="제목을 입력하세요." v-model="title">
                        </div>
                        <div class="input-box font-notojp">
                          <label class="input-label" for="inputcomment">COMMENT</label>
                          <br>
                          <textarea id="inputcomment" placeholder="보낼 메세지를 입력하세요." v-model="comment" />
                        </div>
                      </div>
                    </slot>
                </div>
                <div class="modal-footer">
                        <Button class="font-notojp" buttonText="보내기" type="submit" @click.native="send" />
                </div>
              </div>
          </div>
        </div>
        </div>
    </transition>
</template>


<script>
import Button from '@/components/common/Button.vue'
import axios from 'axios'
import URL from "@/util/http-common.js"

export default {
  name: 'MessageModal',
  components: {
    Button,
  },
  props: {
    recivername: String,
    Title: String,
    Content: String,
    Sendtype: Number,
  },
  data() {
    return {
      toUsername: "",
      fromUsername: this.$store.getters.getUserData.name,
      title: "",
      comment: "",
      filters: null,
      isActive: false
    }
  },
  created() {
    if (this.Sendtype==1) {
      this.toUsername = this.recivername
    } else if (this.Sendtype==2) {
      this.title = this.Title
      this.comment = this.Content
    }
    else {
      this.toUsername = this.recivername
      this.title = this.Title
      this.comment = this.Content
    }
  },
  methods: {
    changeValue(str) {
      document.querySelector('.s').value = str;
      this.toUsername = str
      this.isActive = false;
    },
    selectValue(keycode, str) {
        if (this.isActive) {
            const hasClass = document.querySelector('.r').classList.contains('key')
            if (keycode === 'down') {
                if (!hasClass) {
                    const thisEl = document.querySelectorAll('.r li')[0]
                    document.querySelector('.r').classList.add('key')
                    thisEl.classList.add('sel')
                    thisEl.focus()
                } else {
                    const lastEl = document.querySelector('.r li:last-child');
                    const thisEl = document.querySelector('.r li.sel');
                    const nextEl = thisEl.nextElementSibling;
                    if (!lastEl.classList.contains('sel')) {
                    thisEl.classList.remove('sel');
                    nextEl.classList.add('sel');
                    nextEl.focus();
                    }
                }
            }
            if (keycode === 'up' && hasClass) {
                const firstEl = document.querySelectorAll('.r li')[0];
                const thisEl = document.querySelector('.r li.sel');
                const prevEl = thisEl.previousElementSibling;
                if (!firstEl.classList.contains('sel')) {
                    thisEl.classList.remove('sel');
                    prevEl.classList.add('sel');
                    prevEl.focus();
                } else {
                    document.querySelector('.s').focus();
                }
            }
            if (keycode === 'enter' && hasClass) {
                this.changeValue(str);
            }
        }
    },
    send () {
      let sendData = {
        writername: this.fromUsername,
        recivername: this.toUsername,
        title: this.title,
        content: this.comment,
      }
      axios.post(`${URL.BASE_URL}/msg`, sendData)
      .then(res => {
        if (res.data === 'success') {
          alert('전송되었습니다.')
          // this.$router.push('/')
          this.modalclose()
        }
        else {
          alert('일치하는 회원정보가 없습니다.')
        }
      })
      .catch(err => {
        alert(err)
      })
    },

    modalclose () {
      this.$emit('close')
    },
    nameCheck(event) {
      axios.get(`${URL.BASE_URL}/account/total/${event.target.value}`)
      .then(res => {
        this.filters = res.data
        this.isActive = true   
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
}
</script>

<style>
    /* model */
  #messageModal ul{
    padding: 0;
    
  }
  #messageModal li{
    list-style: none;
    width: 100%;
    background-color: rgb(212, 211, 211);
  }
  #messageModal li:hover{
    background-color: darkgray
  }
  #messageModal hr {
    margin-top: 0;
  }
  #messageModal .form button {
    width: 100%;
  }
  #messageModal .modalfooter {
    display: block;
    text-align: right;
    margin: 0 20px;
  }
  #messageModal .input-box {
   margin-bottom: 10px;
  }
  #messageModal .input-box input {
    width: 100%;
    height: 40px;
    border: 0.8px;
    padding-left: 10px;
    border-style: none none solid none;
  }
  #messageModal .input-label {
    font-size: 16px;
    margin: 0 0 10px 0;
    padding: 8px 0 0 0;
    text-align: right;
    font-weight: bolder
  }

  #messageModal .login-type {
    display: flex;
    justify-content: space-around;
  }

  #messageModal .modal-mask {
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

  #messageModal .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  #messageModal .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 10px;
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }
  #messageModal .modal-header {
    padding-top: 0;
    padding-bottom: 0;
  }
  #messageModal .modal-header h3 {
    margin-top: 0;
    color: rgb(236,128,116);
  }

  #messageModal .modal-body {
    margin: 0 0;
  }
  #messageModal .modal-body button {
    margin: 10px 0 10px 0;
    /* padding: 10px 0; */
  }
  #messageModal .modal-default-button {
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

  #messageModal .modal-enter {
    opacity: 0;
  }

  #messageModal .modal-leave-active {
    opacity: 0;
  }

  #messageModal .modal-enter .modal-container,
  #messageModal .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }

  #messageModal .error-msg {
  width: 100%;
  float: left;
  color: #EE4B55;
  font-size: 14px;
  }

  #messageModal textarea {
      width: 100%;
      min-height: 100px;
  }
  @media (min-width: 768px) {
    
    #messageModal .modal-container {
      width: 30%;
    }
  }
</style>
