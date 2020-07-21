<template>
  <transition name="modal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">
            <div class="modal-header">
              <h3>logo</h3>
              <button type="button" class="close" data-dismiss="modal" @click="modalclose">&times;</button>
            </div>

            <div class="modal-body">
                <slot name="body">
                  <div class="login-type"> 
                    <div>
                      <input type="radio" id="user" value="USER" v-model="loginData.picked">
                      <label for="user">USER</label>
                    </div>
                    <div>
                      <input type="radio" id="pro" value="PRO" v-model="loginData.picked">
                      <label for="pro">PRO</label>
                    </div>
                  </div>
                  <form action="" method="POST">
                    <div class="input-box">
                      <label class="input-label" for="Inputemail">Email</label>
                      <input type="text" id="Inputemail" placeholder="Email" v-model="loginData.email">
                    </div>
                    <div class="input-box">
                      <label class="input-label" for="Inputpassword">Password</label>
                      <input type="password" id="Inputpassword" placeholder="Password" v-model="loginData.password" @keyup.enter="login">
                    </div>
                    <Button buttonText="Login" type="submit" @click="login" />
                  </form>
                </slot>
            </div>

            <div class="modal-footer">
                <slot name="footer">
                  <p>good!</p>
                </slot>
            </div>
            </div>
        </div>
        </div>
    </transition>
</template>

<script>
import Button from '@/components/common/Button.vue'

export default {
  name: 'LoginModal',
  components: {
    Button,
  },
  data() {
    return {
      loginData: {
        email: null,
        password: null,
        picked: 'USER',
      },
    }
  },
  methods: {
    login () {
      console.log(this.loginData)
    },

    modalclose () {
      this.$emit('close')
    },
  },
}
</script>

<style scoped>
    /* model */
  form button {
    width: 100%;
  }
  .input-box {
   margin-bottom: 10px;
  }
  .input-box input {
    width: 100%;
    height: 40px;
    border-radius: 5px;
  }
  .input-label {
    margin: 0;
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
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header h3 {
    margin-top: 0;
    color: #42b983;
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