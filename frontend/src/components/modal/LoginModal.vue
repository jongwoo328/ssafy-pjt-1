<template>
  <transition name="modal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">
            <div class="modal-header">
              <h3>Login</h3>
              <button type="button" class="close" data-dismiss="modal" @click="modalclose">×</button>
            </div>
            <!-- <h1 @click="login">test</h1> -->
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

    </transition>
</template>


<script>
import Button from '@/components/common/Button.vue'
import axios from 'axios'
import * as EmailValidator from "email-validator";

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
      console.log(1)
      if (this.email === "" || (this.email.length > 0 && !EmailValidator.validate(this.email)))
        this.errorData.email = "올바른 이메일 형식이 아닙니다."
      else this.errorData.email = false
    },
    login () {
      this.formCheck()
      let loginData = {
        email: this.email,
        password: this.password
      }

      axios.post("http://192.168.43.204:8090/account/login", loginData)
      .then(res => {
        console.log(res)
        if (res.status === 200) {
          console.log(res.headers)
          this.$session.set('jwstoken', res.headers.jwstoken)
          this.$store.commit('login')
          this.$router.push('/')
        }
      })
      .catch(err => {
        console.log(err)
        alert(err)
      })
    },

    modalclose () {
      this.$emit('close')
    },
  },
}
</script>


<style scoped>
    /* model */
  hr {
    margin-top: 0;
  }
  .form button {
    width: 100%;
  }
  .modalfooter {
    display: block;
    text-align: right;
    margin: 0 20px;
  }
  .input-box {
   margin-bottom: 25px;
  }
  .input-box input {
    width: 100%;
    height: 40px;
    border: 1.5px solid black;
    font-size: 13px;
    padding: 0 5px 0 5px;
    border-radius: 5px;
  }
  .input-label {
    /* display: none; */
    margin: 5px 0 5px 0;
  }
  .login-type {
    display: flex;
    justify-content: space-around;
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
  .modal-header h3 {
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
  }
</style>