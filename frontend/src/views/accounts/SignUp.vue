<template>
  <div class="signup">
    <span class="signup-text">가입 정보 입력</span>

    <div class="form-block">
        <input class="input-radio" type="radio" id="pro" name="pro" value="pro" @click="pro">
        <label for="pro">Pro</label>
        <input class="input-radio" type="radio" id="user" name="user" value="user" @click="user">
        <label for="user">User</label>
    </div>
    
    <div class="form-block">
        <label class="form-block-head" for="username">
            <em class="asterisk-red">*</em>
            Username:
        </label>
        <input 
        class="input-text"
        v-model="username" 
        id="username" 
        placeholder="유저명을 입력하세요." 
        type="text" 
        autocapitalize="none"
        />
    </div>

    <div class="form-block">
        <label class="form-block-head" for="email">
            <em class="asterisk-red">*</em>
            Email:
        </label>
        <input 
        class="input-text"
        v-model="email" 
        id="email" 
        placeholder="이메일을 입력하세요." 
        type="text" 
        autocapitalize="none"
        />
    </div>

    <div class="form-block">
        <label class="form-block-head" for="password">
            <em class="asterisk-red">*</em>
            Password:
        </label>
        <input 
            class="input-text"
            v-model="password" 
            id="password" 
            placeholder="비밀번호를 입력하세요." 
            type="password"
            autocapitalize="none"
        />
    </div>

    <div class="form-block">
        <label class="form-block-head" for="passwordConfirm">
            <em class="asterisk-red">*</em>
            PasswordConfirm:
        </label>
        <input 
            class="input-text"
            v-model="passwordConfirm" 
            id="passwordConfirm" 
            placeholder="비밀번호 확인" 
            type="password"
            autocapitalize="none"
            @keyup.enter="signUp"
        />
    </div>
    
    <div class="form-block">
        <label class="form-block-head" for="TEL">Tel:</label>
        <input 
            class="input-text"
            v-model="tel" 
            id="tel" 
            placeholder="휴대전화번호" 
            type="text"
            autocapitalize="none"
            @keyup.enter="signUp"
        />
    </div>

    <button 
        class="btn-bottom btn--back btn--login" 
        @click="signUp"  
    >가입하기</button>

  </div>
</template>

<script>

import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from 'axios'
let signUpUrl = '/tmp'

export default {
    name: 'SignUp',
    created() {
      this.passwordSchema
        .is()
        .min(8)
        .is()
        .max(100)
        .has()
        .digits()
        .has()
        .letters();
    },
    data() {
      return {
        username: null,
        password: null,
        passwordConfirm: null,
        email: null,
        tel: null,
        errorData: {
          username: false,
          password: false,
          passwordConfirm: false,
          email: false,
        },
        passwordSchema: new PV(),
        isNormal: false
      }
    },
    // watch: {
    //   password: function(v) {
    //     this.passwordCheck()
    //   },
    //   email: function(v) {
    //     this.emailCheck()
    //   },
    //   username: function(v) {
    //     this.usernameCheck()
    //   },
    //   passwordConfirm: function(v) {
    //     this.passwordConfirmCheck()
    //   }
    // },
    methods: {
      pro() {
          this.isNormal = true
      },
      user() {
          this.isNormal = false
      },
      emailCheck() {
        if (this.email >= 0 && !EmailValidator.validate(this.email))
          this.errorData.email = "올바른 이메일 형식이 아닙니다."
        else this.errorData.email = false
      },
      passwordCheck() {
        if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
          this.errorData.password = "비밀번호는 영문, 숫자포함 8자리 이상이어야 합니다."
        else this.errorData.password = false
      },
      usernameCheck() {
        if (this.username.length < 3 || this.username.length > 8)
          this.errorData.username = "username은 3자이상 8자 이하여야 합니다."
        else this.errorData.username = false
      },
      passwordConfirmCheck() {      
        if (this.passwordConfirm > 0 && this.passwordConfirm !== this.password)
          this.errorData.passwordConfirm = "비밀번호가 일치하지 않습니다."
        else this.errorData.passwordConfirm = false
      },
      signUp() {
        console.log(this.isNormal)
        let signUpData = {
          'email': this.email,
          'username': this.username,
          'password': this.password,
          'tel': this.tel
        }

        console.log(signUpData)
        if (this.username === null || this.username.length > 8){
          alert('닉네임은 1글자 이상 8글자 이하여야 합니다.')
          return
        }
        if (this.email === null || this.email.length > 128){
          alert('이메일은 128자 이하여야 합니다.')
          return
        }
        if (this.password !== this.passwordConfirm) {
            alert('비밀번호와 비밀번호 확인이 맞지 않습니다.')
            return
        }
        if (this.password.length < 8 || this.password.length > 128) {
            alert('비밀번호는 영문, 숫자 포함 8글자 이상 128글자 이하여야 합니다.')
            return
        }

        axios.post(signUpUrl, signUpData)
        .then(res => {
          this.$router.push('/')
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
      }
    }
}
</script>

<style scoped>
.signup {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 30px;
}
.signup-text {
    font-size: 30px;
    font-weight: bolder;
}
.form-block {
    text-align: left;
    margin: 13px 0 13px 0;
}

.form-block-head {
    display: block;
    font-size: 16px;
}

.asterisk-red {
    color: red;
}

.input-text {
    height: 40px;
    width: 30vw;
}

.input-radio {
    margin-left: 2vw;
}
</style>