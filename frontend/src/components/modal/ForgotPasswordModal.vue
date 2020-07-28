<template>
  <transition name="modal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">
            <div class="modal-header">
              <h4>Find your password</h4>
              <button type="button" class="close" data-dismiss="modal" @click="modalclose">&times;</button>
            </div>

            <div class="modal-body">
                <slot name="body">
                  <form class="form" @submit.prevent="find">
                      <label class="input-label" for="inputemail">Email</label>
                      <input name="inputemail" type="text" id="Inputemail" class="mb-0" placeholder="이메일을 입력하세요" v-model="email">
                      <div v-if="errorData.email" class="error-msg" v-text="errorData.email"></div>
                      <input type="text" name="password" id="password">
                      <input type="text" name="username" id="username">
                      <Button v-if="this.isfind" buttonText="Login" type="submit" data-dismiss="modal" @click.native="$emit('change')" />
                      <Button v-else buttonText="Back" type="submit" data-dismiss="modal" @click.native="$emit('change')" />
                      <Button buttonText="Submit" type="submit"/>
                  </form>
                </slot>
            </div>
            </div>
        </div>
        </div>
    </transition>
</template>

<script>
import Button from '@/components/common/Button.vue'
import * as EmailValidator from "email-validator";
import axios from 'axios'
import emailjs from 'emailjs-com';

export default {
  name: 'ForgotPasswordModal',
  components: {
    Button,
  },
  data() {
    return {
      email: "",
      errorData: {
        email: ""
      },
      isfind: false,
      password: ""
    }
  },
  watch: {
    email: function() {
      this.formCheck()
    }
  },
  methods: {
    formCheck() {
      if (this.email === "" || (this.email.length > 0 && !EmailValidator.validate(this.email)))
        this.errorData.email = "올바른 이메일 형식이 아닙니다."
      else this.errorData.email = false
    },
    find (e) {
      this.formCheck()

      let forgotData = {
        email: this.email,
      }
      axios.post('http://192.168.100.88:8090/account/pwfind', forgotData)
      .then(res => {
        console.log(res)
        if (res.status === 200) {
          console.log(res)
          if (res.data.status === true) {
            this.isfind = true
            document.getElementById('password').value = res.data.data
            document.getElementById('username').value = 'test'

            console.log(process.env.VUE_APP_USER_ID)
            console.log(process.env.VUE_APP_TEMPLATE_KEY)
            emailjs.sendForm('gmail', process.env.VUE_APP_TEMPLATE_KEY, e.target, process.env.VUE_APP_USER_ID)
            .then((result) => {
                console.log('SUCCESS!', result.status, result.text);
            }, (error) => {
                console.log('FAILED...', error);
            })
            alert('임시비밀번호를 발송하였습니다.')
          }
          else if (res.data.data === 'email') {
            alert('입력하신 메일 정보가 올바르지 않습니다.')
          }
        }
      })
    },
    modalclose () {
      this.$emit('close')
    }
  },
}
</script>

<style scoped>
    /* model */
  #password, #username {
    display: none;
  }
  .form {
    text-align: right;
  }
  .button {
    display: flex;
    justify-content: space-between;
  }
  .button :first-child {
    color: rgb(236,128,116);
    background-color: white;
  }
  .modalfooter {
    display: block;
    text-align: right;
    margin: 0 20px;
  }
  .input-box {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 25px;
  }
  .input-box input {
    width: 100%;
    height: 40px;
    border: 1.5px solid black;
    font-size: 13px;
    margin-bottom: 20px;
    padding: 0 5px 0 5px;
    border-radius: 5px;
  }
  .input-label {
    /* display: none; */
    text-align: left;
    margin: 5px 0 5px 0;
  }
  .modal-mask {
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

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 10px;
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }
  .modal-header {
    padding-top: 0;
    padding-bottom: 0;
  }
  .modal-header h4 {
    margin-top: 0;
    color: rgb(236,128,116);
  }

  .modal-body {
    margin: 0 0;
  }

  .modal-default-button {
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

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }

  .error-msg {
  width: 100%;
  float: left;
  color: #EE4B55;
  font-size: 14px;
  text-align: left;
  }
</style>