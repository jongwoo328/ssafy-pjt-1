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
                  <div class="form">
                    <div class="input-box">
                      <label class="input-label" for="inputemail">Email</label>
                      <input type="text" id="Inputemail" placeholder="이메일을 입력하세요" v-model="ForgotData.email">
                      <label class="input-label" for="inputtel">Tel</label>
                      <input type="text" id="Inputtel" placeholder="휴대전화번호를 입력하세요. ('-' 제외)" v-model="ForgotData.tel">
                    </div>
                    <div class="button">
                      <Button v-if="this.isfind" buttonText="Login" type="submit" data-dismiss="modal" @click.native="$emit('change')" />
                      <Button v-else buttonText="Back" type="submit" data-dismiss="modal" @click.native="$emit('change')" />
                      <Button buttonText="Submit" type="submit" @click.native="find" />
                    </div>
                  </div>
                </slot>
            </div>

            <!-- <div class="modal-footer">
                <slot name="footer">
                  <a class="font-kor" data-dismiss="modal" href="#" @click="$emit('change')">Back</a>
                </slot>
            </div> -->
            </div>
        </div>
        </div>
    </transition>
</template>

<script>
import Button from '@/components/common/Button.vue'
import axios from 'axios'

export default {
  name: 'ForgotPasswordModal',
  components: {
    Button,
  },
  data() {
    return {
      ForgotData: {
        email: null,
        tel: null,
      },
      isfind: false,
    }
  },
  methods: {
    find () {
      console.log(this.ForgotData)
      axios.post('http://192.168.43.109:3000/account/pwfind', this.ForgotData)
      .then(res => {
        console.log(res)
        if (res.status === 200) {
          console.log(res)
          if (res.data.status === true) {
            this.isfind = true
            alert(`임시비밀번호는 '${res.data.data}'입니다.`)
          }
        }
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
</style>