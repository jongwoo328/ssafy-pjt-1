<template>
  <transition name="modal">
    <div id="loginModal">
        <div class="modal-mask">
          <div class="modal-wrapper">
              <div class="modal-container">
                <div class="modal-header">
                  <h3>Login</h3>
                  <button type="button" class="close" data-dismiss="modal" @click="modalclose">×</button>
                </div>
                <div class="modal-body">
                    <slot name="body">
                      <div class="form">
                        <div class="input-box font-notojp">
                          <label class="input-label" for="inputname">Email</label>
                          <input type="text" id="inputname" placeholder="이메일을 입력하세요." v-model="email">
                          <div v-if="errorData.email" class="error-msg" v-text="errorData.email"></div>
                        </div>
                        <div class="input-box font-notojp">
                          <label class="input-label" for="inputpassword">Password</label>
                          <input type="password" id="inputpassword" placeholder="패스워드를 입력하세요." v-model="password" @keyup.enter="login">
                        </div>
                        <Button class="font-notojp" buttonText="Login" type="submit" @click.native="login" />
                      </div>
                    </slot>
                </div>
                <hr>
                <div class="modalfooter">
                    <slot name="footer">
                      <a class="font-kor" data-dismiss="modal" href="#" @click="$emit('change')">Forgot password?</a>
                      <br>
                      <a class="font-kor" data-dismiss="modal" href="/accounts/signup">Create account</a>
                    </slot>
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
import * as EmailValidator from "email-validator"
import URL from "@/util/http-common.js"

export default {
  name: 'LoginModal',
  components: {
    Button,
  },
  watch: {
    email: function() {
      this.formCheck()
    }
  },
  data() {
    return {
      email: "",
      password: "",
      errorData: {
        email: false,
      }
    }
  },
  methods: {
    formCheck() {
      if (this.email === "" || (this.email.length > 0 && !EmailValidator.validate(this.email)))
        this.errorData.email = "올바른 이메일 형식이 아닙니다."
      else this.errorData.email = false
    },
    login () {
      this.formCheck()
      let loginData = {
        email: this.email,
        pw: this.password
      }

      axios.post(`${URL.BASE_URL}/account/login`, loginData)
      .then(res => {
        console.log(res)
        if (res.data.status === true) {
          this.$session.set('jwstoken', res.headers.jwstoken)
          this.$store.commit('login', res.data.object)
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
  },
}
</script>

<style>
    /* model */
  #loginModal hr {
    margin-top: 0;
  }
  #loginModal .form button {
    width: 100%;
  }
  #loginModal .modalfooter {
    display: block;
    text-align: right;
    margin: 0 20px;
  }
  #loginModal .input-box {
   margin-bottom: 10px;
  }
  #loginModal .input-box input {
    width: 100%;
    height: 40px;
    border: 0.8px;
    padding-left: 10px;
    border-style: none none solid none;
  }
  #loginModal .input-label {
    font-size: 16px;
    margin: 0 0 10px 0;
    padding: 8px 0 0 0;
    text-align: right;
    font-weight: bolder
  }

  #loginModal .login-type {
    display: flex;
    justify-content: space-around;
  }

  #loginModal .modal-mask {
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

  #loginModal .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  #loginModal .modal-container {
    width: 350px;
    min-width: 300px;
    margin: 0px auto;
    padding: 20px 10px;
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 2px 8px rgba(155, 121, 121, 0.33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }
  #loginModal .modal-header {
    padding-top: 0;
    padding-bottom: 0;
  }
  #loginModal .modal-header h3 {
    margin-top: 0;
    color: rgb(236,128,116);
  }

  #loginModal .modal-body {
    margin: 0 0;
  }
  #loginModal .modal-body button {
    margin: 10px 0 10px 0;
    /* padding: 10px 0; */
  }
  #loginModal .modal-default-button {
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

  #loginModal .modal-enter {
    opacity: 0;
  }

  #loginModal .modal-leave-active {
    opacity: 0;
  }

  #loginModal .modal-enter .modal-container,
  #loginModal .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }

  #loginModal .error-msg {
  width: 100%;
  float: left;
  color: #EE4B55;
  font-size: 14px;
  }
  @media (min-width: 768px) {
    
    #loginModal .modal-container {
      width: 20%;
    }
  }
</style>